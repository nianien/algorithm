# 279.perfect-squares
# 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。 
# 
#  给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。 
# 
#  完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
#  
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：n = 12
# 输出：3 
# 解释：12 = 4 + 4 + 4 
# 
#  示例 2： 
# 
#  
# 输入：n = 13
# 输出：2
# 解释：13 = 4 + 9 
#  
# 
#  提示： 
# 
#  
#  1 <= n <= 104 
#  
#  Related Topics 广度优先搜索 数学 动态规划 
#  👍 886 👎 0

import math


# leetcode submit region begin(Prohibit modification and deletion)


def is_square(n: int) -> bool:
    return math.pow(math.floor(math.sqrt(n)), 2) == n


class Solution:
    def numSquares(self, n: int) -> int:
        """
            f(n)=min{f(n-k)}+1, 其中k为完全平方数
            在求f(n)时,分别比较 f(n-k), k=1,4,9...且k^2<n,然后取最小值加1
            注意,如果n本身为平方数,则f(n)=1
        :param n:
        :return:
        """
        dp = list[int]()
        for i in range(1, n + 1):
            if is_square(i):
                dp.append(1)
            else:
                k = int(math.sqrt(i))
                # dp[i]|<n-k>=dp[i - k * k]+1
                dp_minus_k = [dp[i - k * k - 1] + 1 for k in range(1, k + 1)]
                dp.append(min(dp_minus_k))
        return dp[-1]


# leetcode submit region end(Prohibit modification and deletion)


# test from here
if __name__ == '__main__':
    print(Solution().numSquares(13))
    print(Solution().numSquares(12))
