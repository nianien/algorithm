# 343.integer-break
# 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。 
# 
#  示例 1: 
# 
#  输入: 2
# 输出: 1
# 解释: 2 = 1 + 1, 1 × 1 = 1。 
# 
#  示例 2: 
# 
#  输入: 10
# 输出: 36
# 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。 
# 
#  说明: 你可以假设 n 不小于 2 且不大于 58。 
#  Related Topics 数学 动态规划 
#  👍 524 👎 0


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def integerBreak2(self, n: int) -> int:
        if n < 3:
            return 1
        if n == 3:
            return 2
        d = n // 3
        v = n % 3
        if v == 1:
            return (3 ** (d - 1)) * 4
        if v == 0:
            return 3 ** d
        return (3 ** d) * v

    def integerBreak(self, n: int) -> int:
        """
         dp[i]=max{ max(i-j,dp[i-j])*j} j=2,3...i-2
        :param n:
        :return:
        """
        # f(2)=1,f(3)=2
        if n <= 0:
            return 0
        dp = [0]
        for i in range(2, n + 1):
            # 这里i必须拆解成至少两个正整数,所以必须j>=1 &&j<i
            # dp[i]|<i-j>=max(dp[i-j],i-j)*j
            dp_minus_j = [max(dp[i - j - 1], i - j) * j for j in range(1, i)]
            dp.append(max(dp_minus_j))
        return dp[-1]


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().integerBreak(3))
    print(Solution().integerBreak2(3))
