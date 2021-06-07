# 14.longest-common-prefix
# 编写一个函数来查找字符串数组中的最长公共前缀。 
# 
#  如果不存在公共前缀，返回空字符串 ""。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：strs = ["flower","flow","flight"]
# 输出："fl"
#  
# 
#  示例 2： 
# 
#  
# 输入：strs = ["dog","racecar","car"]
# 输出：""
# 解释：输入不存在公共前缀。 
# 
#  
# 
#  提示： 
# 
#  
#  0 <= strs.length <= 200 
#  0 <= strs[i].length <= 200 
#  strs[i] 仅由小写英文字母组成 
#  
#  Related Topics 字符串 
#  👍 1637 👎 0

from typing import List


# leetcode submit region begin(Prohibit modification and deletion)

class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        # s1 = min(strs)
        # s2 = max(strs)
        # for i, x in enumerate(s1):
        #     if x != s2[i]:
        #         return s2[:i]
        # return s1
        if not strs:
            return ""
        for i in range(len(strs[0])):
            if any(i == len(s) or s[i] != strs[0][i] for s in strs):
                return strs[0][:i]
        return strs[0]


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().longestCommonPrefix(["flower", "flow", "flight"]))
    print(Solution().longestCommonPrefix(["ab", "a"]))
