package io.lining;

import java.util.List;

/**
 * @author : liyifei
 * @created : 26/2/2026, 星期四
 * Copyright (c) 2004-2029 All Rights Reserved.
 **/
public class RedisRateLimiter {

    // ============================================================
// Redis 客户端接口
// ============================================================

    interface RedisClient {
        long incr(String key);

        void pexpire(String key, long millis);

        Object eval(String script, List<String> keys, List<String> args);
    }

// ============================================================
// 统一接口
// ============================================================

    interface RateLimiter {
        boolean allow(String key);
    }

// ============================================================
// 1. 固定窗口 — 不需要 Lua，INCR 天然原子
// ============================================================

    class FixedWindowRateLimiter implements RateLimiter {

        private final int limit;
        private final long windowMillis;
        private final RedisClient redis;

        public FixedWindowRateLimiter(int limit, long windowMillis, RedisClient redis) {
            this.limit = limit;
            this.windowMillis = windowMillis;
            this.redis = redis;
        }

        @Override
        public boolean allow(String key) {
            long windowId = System.currentTimeMillis() / windowMillis;
            String rk = "rl:fw:" + key + ":" + windowId;

            long count = redis.incr(rk);
            if (count == 1) redis.pexpire(rk, windowMillis * 2);
            return count <= limit;
        }
    }

// ============================================================
// 2. 滑动窗口计数器 — Lua
//    读当前窗口 + 上一窗口 → 加权 → 判断 → INCR
// ============================================================

    class SlidingWindowRateLimiter implements RateLimiter {

        private final int limit;
        private final long windowMillis;
        private final RedisClient redis;

        private static final String LUA = """
                local curKey = KEYS[1]
                local preKey = KEYS[2]
                local now = tonumber(ARGV[1])
                local windowMs = tonumber(ARGV[2])
                local limit = tonumber(ARGV[3])
                
                local cur = tonumber(redis.call('GET', curKey) or '0')
                local pre = tonumber(redis.call('GET', preKey) or '0')
                
                local weight = (now % windowMs) / windowMs
                local estimated = cur + pre * (1 - weight)
                
                if estimated >= limit then return 0 end
                
                redis.call('INCR', curKey)
                redis.call('PEXPIRE', curKey, windowMs * 3)
                return 1
                """;

        public SlidingWindowRateLimiter(int limit, long windowMillis, RedisClient redis) {
            this.limit = limit;
            this.windowMillis = windowMillis;
            this.redis = redis;
        }

        @Override
        public boolean allow(String key) {
            long now = System.currentTimeMillis();
            long windowId = now / windowMillis;

            Object r = redis.eval(LUA,
                    List.of("rl:sw:" + key + ":" + windowId,
                            "rl:sw:" + key + ":" + (windowId - 1)),
                    List.of(String.valueOf(now),
                            String.valueOf(windowMillis),
                            String.valueOf(limit)));
            return Long.valueOf(1L).equals(r);
        }
    }

// ============================================================
// 3. 令牌桶 — Lua
//    读 tokens + ts → 补充 → 扣减 → 写回
// ============================================================

    class TokenBucketRateLimiter implements RateLimiter {

        private final int capacity;
        private final double refillPerMillis;
        private final RedisClient redis;

        private static final String LUA = """
                local key = KEYS[1]
                local now = tonumber(ARGV[1])
                local capacity = tonumber(ARGV[2])
                local refillPerMs = tonumber(ARGV[3])
                
                local tokens = tonumber(redis.call('HGET', key, 'tk') or ARGV[2])
                local ts = tonumber(redis.call('HGET', key, 'ts') or ARGV[1])
                
                local elapsed = now - ts
                if elapsed > 0 then
                    tokens = math.min(capacity, tokens + elapsed * refillPerMs)
                    ts = now
                end
                
                if tokens >= 1 then
                    tokens = tokens - 1
                    redis.call('HSET', key, 'tk', tostring(tokens), 'ts', tostring(ts))
                    return 1
                end
                
                redis.call('HSET', key, 'tk', tostring(tokens), 'ts', tostring(ts))
                return 0
                """;

        public TokenBucketRateLimiter(double permitsPerSecond, int capacity, RedisClient redis) {
            this.capacity = capacity;
            this.refillPerMillis = permitsPerSecond / 1000.0;
            this.redis = redis;
        }

        @Override
        public boolean allow(String key) {
            long now = System.currentTimeMillis();

            Object r = redis.eval(LUA,
                    List.of("rl:tb:" + key),
                    List.of(String.valueOf(now),
                            String.valueOf(capacity),
                            String.valueOf(refillPerMillis)));
            return Long.valueOf(1L).equals(r);
        }
    }

// ============================================================
// 4. 漏桶 — Lua
//    读 nextAllowed → 判断 → 更新
// ============================================================

    class LeakyBucketRateLimiter implements RateLimiter {

        private final long intervalMillis;
        private final RedisClient redis;

        private static final String LUA = """
                local key = KEYS[1]
                local now = tonumber(ARGV[1])
                local interval = tonumber(ARGV[2])
                
                local next = tonumber(redis.call('GET', key) or '0')
                
                if now >= next then
                    redis.call('SET', key, tostring(now + interval), 'PX', interval * 10)
                    return 1
                end
                return 0
                """;

        public LeakyBucketRateLimiter(double permitsPerSecond, RedisClient redis) {
            this.intervalMillis = (long) (1000.0 / permitsPerSecond);
            this.redis = redis;
        }

        @Override
        public boolean allow(String key) {
            long now = System.currentTimeMillis();

            Object r = redis.eval(LUA,
                    List.of("rl:lb:" + key),
                    List.of(String.valueOf(now),
                            String.valueOf(intervalMillis)));
            return Long.valueOf(1L).equals(r);
        }
    }

// ============================================================
// 5. GCRA — Lua
//    读 TAT → 判断 → 更新
// ============================================================

    class GcraRateLimiter implements RateLimiter {

        private final long T;
        private final long tau;
        private final RedisClient redis;

        private static final String LUA = """
                local key = KEYS[1]
                local now = tonumber(ARGV[1])
                local T = tonumber(ARGV[2])
                local tau = tonumber(ARGV[3])
                
                local tat = tonumber(redis.call('GET', key) or ARGV[1])
                
                if now < tat - tau then return 0 end
                
                local newTat = math.max(now, tat) + T
                redis.call('SET', key, tostring(newTat), 'PX', tau + T + 5000)
                return 1
                """;

        public GcraRateLimiter(double permitsPerSecond, int burstPermits, RedisClient redis) {
            this.T = (long) (1000.0 / permitsPerSecond);
            this.tau = burstPermits * T;
            this.redis = redis;
        }

        @Override
        public boolean allow(String key) {
            long now = System.currentTimeMillis();

            Object r = redis.eval(LUA,
                    List.of("rl:gcra:" + key),
                    List.of(String.valueOf(now),
                            String.valueOf(T),
                            String.valueOf(tau)));
            return Long.valueOf(1L).equals(r);
        }
    }
}