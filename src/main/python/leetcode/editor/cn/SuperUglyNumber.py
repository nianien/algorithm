# 313.super-ugly-number
"""
超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。 

 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。 

 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。 

 

 示例 1： 

 
输入：n = 12, primes = [2,7,13,19]
输出：32 
解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28
,32] 。 

 示例 2： 

 
输入：n = 1, primes = [2,3,5]
输出：1
解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
 

 

 
 
 
 提示： 
 
 
 

 
 1 <= n <= 10⁵ 
 1 <= primes.length <= 100 
 2 <= primes[i] <= 1000 
 题目数据 保证 primes[i] 是一个质数 
 primes 中的所有值都 互不相同 ，且按 递增顺序 排列 
 

 Related Topics 数组 数学 动态规划 👍 392 👎 0

"""
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def nthSuperUglyNumber(self, n: int, primes: List[int]) -> int:
        """
        定义dp[i]表示第i+1个丑数, 其中第1个丑数dp[0]=1
        定义px[j]表示第j个质数因子的最大位置, 初始为0
        则dp[i+1]应该是px[j]指向的丑数乘以对应的质因数,取这些乘积中的最小值
        :param n:
        :param primes: 这里不需要排序和去重
        :return:
        """
        # dp[i]表示第i个丑数
        dp = [0] * n
        # 1 is a super ugly number for any given primes.
        dp[0] = 1
        # 当前丑数对应每个质数因子的最大位置
        px = [0] * len(primes)
        for i in range(1, n):
            dp[i] = min([dp[px[j]] * primes[j] for j in range(len(primes))])
            for j in range(len(primes)):
                if dp[i] == dp[px[j]] * primes[j]:
                    px[j] += 1
        return dp[n - 1]


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().nthSuperUglyNumber(12, [19, 13, 7, 2]))
