# 3.longest-substring-without-repeating-characters
"""
Given a string s, find the length of the longest substring without duplicate 
characters. 

 
 Example 1: 

 
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and 
"cab" are also correct answers.
 

 Example 2: 

 
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
 

 Example 3: 

 
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a 
substring.
 

 
 Constraints: 

 
 0 <= s.length <= 5 * 10⁴ 
 s consists of English letters, digits, symbols and spaces. 
 

 Related Topics Hash Table String Sliding Window 👍 43998 👎 2156

"""


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        cur = 0
        best = 0
        last = {}
        for i, ch in enumerate(s):
            # 1.修正（保证窗口内无重复）
            if ch in last and last[ch] >= cur:
                cur = last[ch] + 1
            # 2.记录字符位置
            last[ch] = i
            # 3.记录长度（修正后的窗口）
            best = max(best, i - cur + 1)
        # print(s[b:e])
        return best

    def lengthOfLongestSubstring_v0(self, s: str) -> int:
        cur = 0
        best = 0
        last = {}
        for i, ch in enumerate(s):
            if ch in last and last[ch] >= cur:
                if best <= i - cur:
                    best = i - cur
                cur = last[ch] + 1
            last[ch] = i
        if best < len(s) - cur:
            best = len(s) - cur
        # print(s[b:e])
        return best


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    # print(Solution().lengthOfLongestSubstring("bbtablud"))
    print(Solution().lengthOfLongestSubstring("asljlj"))
    # print(Solution().lengthOfLongestSubstring("pwwkew"))
