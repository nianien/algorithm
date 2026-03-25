package io.lining;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongSupplier;

/**
 * 面试级限流器全家桶 —— 纯 JDK，per-key，线程安全
 * <p>
 * 包含 5 种经典算法：
 * 1. FixedWindowRateLimiter       — 固定窗口
 * 2. SlidingWindowRateLimiter     — 滑动窗口计数器
 * 3. TokenBucketRateLimiter       — 令牌桶
 * 4. LeakyBucketRateLimiter       — 漏桶
 * 5. GcraRateLimiter              — GCRA (Generic Cell Rate Algorithm)
 * <p>
 * 设计原则：
 * - 可测试：通过注入时钟源替代 System.nanoTime()
 * - 无锁优先：GCRA 使用 CAS；其余使用 per-key synchronized（面试简洁优先）
 * - 不引入任何第三方库
 */

// ============================================================
// 统一接口
// ============================================================

interface RateLimiter {
    boolean allow(String key);
}

// ============================================================
// 1. 固定窗口 (Fixed Window)
// ============================================================

class Window {
    long windowStart;
    int count;
    int preCount;

    public Window(long windowStart) {
        this.windowStart = windowStart;
    }

    public Window() {
        this.windowStart = Helper.clock.getAsLong();
    }
}


class Helper {
    static LongSupplier clock = System::nanoTime;
}


class FixedWindowRateLimiter implements RateLimiter {

    private final int limit;
    private final long windowNanos;

    private final ConcurrentHashMap<String, Window> map = new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(int limit, long windowMillis) {
        if (limit <= 0) throw new IllegalArgumentException("limit must be > 0");
        if (windowMillis <= 0) throw new IllegalArgumentException("windowMillis must be > 0");
        this.limit = limit;
        this.windowNanos = windowMillis * 1_000_000L;
    }

    @Override
    public boolean allow(String key) {
        final long now = Helper.clock.getAsLong();
        final Window window = map.computeIfAbsent(key, _ -> new Window(now));

        synchronized (window) {
            long elapsed = now - window.windowStart;

            // 窗口过期：对齐到窗口边界（而不是 windowStart = now）
            if (elapsed >= windowNanos) {
                long periods = elapsed / windowNanos;
                window.windowStart += periods * windowNanos;
                window.count = 0;
            }

            if (window.count < limit) {
                window.count++;
                return true;
            }
            return false;
        }
    }
}

// ============================================================
// 2. 滑动窗口计数器 (Sliding Window Counter)
// ============================================================

class SlidingWindowRateLimiter implements RateLimiter {

    private final int limit;
    private final long windowNanos;

    private final ConcurrentHashMap<String, Window> map = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int limit, long windowMillis) {
        if (limit <= 0) throw new IllegalArgumentException("limit must be > 0");
        if (windowMillis <= 0) throw new IllegalArgumentException("windowMillis must be > 0");
        this.limit = limit;
        this.windowNanos = windowMillis * 1_000_000L;
    }

    @Override
    public boolean allow(String key) {
        final long now = Helper.clock.getAsLong();
        final Window window = map.computeIfAbsent(key, __ -> new Window(now));

        synchronized (window) {
            long elapsed = now - window.windowStart;

            if (elapsed >= windowNanos) {
                long periods = elapsed / windowNanos;
                if (periods >= 2) {
                    // 跨了2个以上窗口，上个窗口数据已无意义
                    window.preCount = 0;
                } else {
                    // 恰好翻了1个窗口
                    window.preCount = window.count;
                }
                window.count = 0;
                window.windowStart += periods * windowNanos;
                elapsed = now - window.windowStart;
            }

            double weight = (double) elapsed / windowNanos;
            double estimated = window.count + window.preCount * (1.0 - weight);

            if (estimated < limit) {
                window.count++;
                return true;
            }
            return false;
        }
    }
}

// ============================================================
// 3. 令牌桶 (Token Bucket)
// ============================================================

class TokenBucketRateLimiter implements RateLimiter {

    private final double capacity;
    private final double refillPerNano;
    private final boolean warmUp;        // true = 冷启动从 0 开始

    private static class Bucket {
        double tokens;
        long lastRefillTime;
    }

    private final ConcurrentHashMap<String, Bucket> map = new ConcurrentHashMap<>();


    public TokenBucketRateLimiter(double permitsPerSecond, int burst) {
        this(permitsPerSecond, burst, false);
    }

    /**
     * @param permitsPerSecond 每秒放入的令牌数
     * @param burst            桶容量（最大突发量）
     * @param warmUp           true = 新 key 从 0 令牌开始（冷启动）
     */
    public TokenBucketRateLimiter(double permitsPerSecond, int burst, boolean warmUp) {
        if (permitsPerSecond <= 0) throw new IllegalArgumentException("permitsPerSecond must be > 0");
        if (burst <= 0) throw new IllegalArgumentException("burst must be > 0");
        this.capacity = burst;
        this.refillPerNano = permitsPerSecond / 1_000_000_000.0;
        this.warmUp = warmUp;
    }

    @Override
    public boolean allow(String key) {
        final long now = Helper.clock.getAsLong();
        final Bucket bucket = map.computeIfAbsent(key, k -> {
            Bucket b = new Bucket();
            b.tokens = warmUp ? 0 : capacity;
            b.lastRefillTime = now;
            return b;
        });

        synchronized (bucket) {
            long elapsed = now - bucket.lastRefillTime;
            if (elapsed > 0) {
                bucket.tokens = Math.min(capacity, bucket.tokens + elapsed * refillPerNano);
                bucket.lastRefillTime = now;
            }

            if (bucket.tokens >= 1.0) {
                bucket.tokens -= 1.0;
                return true;
            }
            return false;
        }
    }
}

// ============================================================
// 4. 漏桶 (Leaky Bucket)
// ============================================================

class LeakyBucketRateLimiter implements RateLimiter {

    private final long intervalNanos;  // 用纳秒避免整数除法精度丢失
    private final long burst;  // 用纳秒避免整数除法精度丢失

    private static class Bucket {
        long nextAllowedTime;

        public Bucket(long nextAllowedTime) {
            this.nextAllowedTime = nextAllowedTime;
        }
    }

    private final ConcurrentHashMap<String, Bucket> map = new ConcurrentHashMap<>();


    public LeakyBucketRateLimiter(double permitsPerSecond, int burstPermits) {
        if (permitsPerSecond <= 0) throw new IllegalArgumentException("permitsPerSecond must be > 0");
        if (burstPermits < 0) throw new IllegalArgumentException("burstPermits must be >= 0");
        this.intervalNanos = (long) (TimeUnit.SECONDS.toNanos(1) / permitsPerSecond);
        this.burst = Math.multiplyExact(burstPermits, intervalNanos);
    }

    @Override
    public boolean allow(String key) {
        final long now = Helper.clock.getAsLong();
        final Bucket bucket = map.computeIfAbsent(key, _ -> new Bucket(now));
        synchronized (bucket) {
            long allowAt =  bucket.nextAllowedTime - burst;
            if (now < allowAt) {
                return false;
            }
            bucket.nextAllowedTime = Math.max(now, bucket.nextAllowedTime) + intervalNanos;
            return true;
        }
    }
}

// ============================================================
// 5. GCRA (Generic Cell Rate Algorithm)
// ============================================================

class GcraRateLimiter implements RateLimiter {

    private final long T;     // 请求间隔 (ns)
    private final long tau;   // 突发容忍窗口 (ns)

    // 无锁设计：CAS 自旋
    private final ConcurrentHashMap<String, AtomicLong> tatByKey = new ConcurrentHashMap<>();


    /**
     * @param permitsPerSecond 平均速率
     * @param burstPermits     突发许可数（实际首次突发 = burstPermits + 1）
     */
    public GcraRateLimiter(double permitsPerSecond, int burstPermits) {
        if (permitsPerSecond <= 0) throw new IllegalArgumentException("permitsPerSecond must be > 0");
        if (burstPermits < 0) throw new IllegalArgumentException("burstPermits must be >= 0");
        this.T = (long) (TimeUnit.SECONDS.toNanos(1) / permitsPerSecond);
        this.tau = Math.multiplyExact(burstPermits, this.T);
    }

    @Override
    public boolean allow(String key) {
        final long now = Helper.clock.getAsLong();

        // 初始 TAT 设为 now 而不是 0，避免与 nanoTime 不同源的问题
        final AtomicLong tatRef = tatByKey.computeIfAbsent(key, k -> new AtomicLong(now));

        while (true) {
            final long tat = tatRef.get();
            final long allowAt = tat - tau;

            // 请求来得太早，拒绝
            if (now < allowAt) {
                return false;
            }

            final long newTat = Math.max(now, tat) + T;

            if (tatRef.compareAndSet(tat, newTat)) {
                return true;
            }
            // CAS 失败，自旋重试
            Thread.onSpinWait();  // Java 9+，降低自旋 CPU 开销
        }
    }
}

// ============================================================
// Demo & 验证
// ============================================================

public class RateLimiters {

    public static void main(String[] args) throws Exception {
        System.out.println("===== Fixed Window (5 req / 500ms) =====");
        demo(new FixedWindowRateLimiter(5, 500));

        System.out.println("\n===== Sliding Window (5 req / 500ms) =====");
        demo(new SlidingWindowRateLimiter(5, 500));

        System.out.println("\n===== Token Bucket (10 rps, burst=5) =====");
        demo(new TokenBucketRateLimiter(10.0, 5));

        System.out.println("\n===== Leaky Bucket (10 rps) =====");
        demo(new LeakyBucketRateLimiter(10.0, 0));

        System.out.println("\n===== GCRA (10 rps, burst=3) =====");
        demo(new GcraRateLimiter(10.0, 3));
    }

    private static void demo(RateLimiter rl) throws Exception {
        String key = "user1";

        // 突发测试
        System.out.print("Burst:  ");
        for (int i = 0; i < 10; i++) {
            System.out.print(rl.allow(key) ? "✓ " : "✗ ");
        }
        System.out.println();

        // 等一会儿再试
        Thread.sleep(600);
        System.out.print("After wait: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(rl.allow(key) ? "✓ " : "✗ ");
        }
        System.out.println();
    }
}