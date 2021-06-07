# 22.generate-parentheses
# 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：n = 3
# 输出：["((()))","(()())","(())()","()(())","()()()"]
#  
# 
#  示例 2： 
# 
#  
# 输入：n = 1
# 输出：["()"]
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= n <= 8 
#  
#  Related Topics 字符串 回溯算法 
#  👍 1822 👎 0
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        """
        dp[i]表示i组括号的所有有效组合
        dp[i] = "(dp[p])+dp[q]", 其中,p+q=n-1,p,q>=0
        :param n:
        :return:
        """
        # n=0时,括号为""
        # n=1时, 只有一种结果"()"
        dp = [[] for _ in range(n + 1)]
        dp[0] = [""]
        for i in range(1, n + 1):
            # 遍历dp[i-1]中p和q的组合情况
            # dp[j]表示左边为j个括号对,右边为i-j-i个括号对,
            # 注意, 这里p和q的括号对有多种排列可能, 需要遍历组合
            for j in range(i):
                for p in dp[j]:
                    for q in dp[i - 1 - j]:
                        dp[i].append("({0}){1}".format(p, q))
        return dp[n]


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().generateParenthesis(3))
