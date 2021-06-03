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

        def backtrack(idx: int):
            """
            idx为当前按键位置
            :param idx:
            :return:
            """
            if idx == n:
                res.append("".join(temp))
                return
            # print("i=>", i)
            for ch in letters[int(digits[idx]) - 2]:
                # 选择完当前按键, 然后选择下一个按键
                temp[idx] = ch
                backtrack(idx + 1)

        letters = ["abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"]
        n = len(digits)
        temp = [''] * n
        res = []
        backtrack(0)
        return res


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().letterCombinations("22"))
