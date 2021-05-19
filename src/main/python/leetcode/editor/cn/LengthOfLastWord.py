# 58.length-of-last-word
# 给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。 
# 
#  单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：s = "Hello World"
# 输出：5
#  
# 
#  示例 2： 
# 
#  
# 输入：s = " "
# 输出：0
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= s.length <= 104 
#  s 仅有英文字母和空格 ' ' 组成 
#  
#  Related Topics 字符串 
#  👍 313 👎 0

# leetcode submit region begin(Prohibit modification and deletion)
class Solution(object):
    def lengthOfLastWord(self, s):
        """
        :type s: str
        :rtype: int
        """
        count = 0
        for i in range(len(s) - 1, -1, -1):
            if not s[i].isspace():
                count += 1
            elif count != 0:  # count=0,还没开始计数,表示是末尾空格
                break
        return count
        # return len(s.strip().split(" ")[-1])


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().lengthOfLastWord("bbb ca"))
