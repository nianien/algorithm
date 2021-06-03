# 17.letter-combinations-of-a-phone-number
# 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
# 
#  给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
# 
#  
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：digits = "23"
# 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
#  
# 
#  示例 2： 
# 
#  
# 输入：digits = ""
# 输出：[]
#  
# 
#  示例 3： 
# 
#  
# 输入：digits = "2"
# 输出：["a","b","c"]
#  
# 
#  
# 
#  提示： 
# 
#  
#  0 <= digits.length <= 4 
#  digits[i] 是范围 ['2', '9'] 的一个数字。 
#  
#  Related Topics 深度优先搜索 递归 字符串 回溯算法 
#  👍 1341 👎 0
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)

class Solution:

    def letterCombinations(self, digits: str) -> List[str]:
        if not digits:
            return []

        def backtrack(idx: int, temp: List[str]):
            """
            idx为当前按键位置
            :param temp:
            :param idx:
            :return:
            """
            if idx == n:
                res.append("".join(temp))
                return
            # print("i=>", i)
            for ch in keys[int(digits[idx]) - 2]:
                # 选择完当前按键, 然后选择下一个按键
                temp[idx] = ch
                backtrack(idx + 1, temp)

        keys = ["abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"]
        n = len(digits)
        res = []
        backtrack(0, [''] * n)
        return res

    def letterCombinations_by_dp(self, digits):
        """
        动态规划
        dp[i]: 前i个字母的所有组合
        由于dp[i]只与dp[i-1]有关,可以使用变量代替列表存储降低空间复杂度
        :type digits: str
        :rtype: List[str]
        """
        if not digits:
            return []
        keys = ["abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"]
        dp = ['']
        for num in digits:
            # pre表示前i个字母的组合,cur表示当前字母含义
            dp = [pre + cur for pre in dp for cur in keys[int(num)-2]]
        return dp


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().letterCombinations("22"))
    print(Solution().letterCombinations_by_dp("22"))
