# 76.minimum-window-substring
"""
Given two strings s and t of lengths m and n respectively, return the minimum 
window substring of s such that every character in t (including duplicates) is 
included in the window. If there is no such substring, return the empty string "". 


 The testcases will be generated such that the answer is unique. 

 
 Example 1: 

 
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' 
from string t.
 

 Example 2: 

 
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
 

 Example 3: 

 
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

 
 Constraints: 

 
 m == s.length 
 n == t.length 
 1 <= m, n <= 10⁵ 
 s and t consist of uppercase and lowercase English letters. 
 

 
 Follow up: Could you find an algorithm that runs in O(m + n) time? 

 Related Topics Hash Table String Sliding Window 👍 19765 👎 836

"""


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def minWindow_v0(self, s: str, t: str) -> str:
        # needs 表示需要多少个字符
        needs, window = {}, {}
        missing = len(t)
        for c in t:
            needs[c] = needs.get(c, 0) + 1
        l = 0
        b, e = 0, 0
        for i, v in enumerate(s):
            if v in needs:
                window[v] = window.get(v, 0) + 1
                if window[v] <= needs[v]:
                    missing -= 1
            while missing == 0:
                # 更新每一个合法窗口
                if e == 0 or i - l < e - b:
                    b, e = l, i + 1
                c = s[l]
                if c in needs:
                    window[c] -= 1
                    if window[c] < needs[c]:
                        missing += 1
                l += 1
        return s[b:e]

    def minWindow(self, s: str, t: str) -> str:
        # needs 表示还差多少个字符
        needs = {}
        missing = len(t)
        for c in t:
            needs[c] = needs.get(c, 0) + 1
        l = 0
        b, e = 0, 0
        for i, v in enumerate(s):
            if v in needs:
                needs[v] -= 1
                if needs[v] >= 0:
                    missing -= 1
            while missing == 0:
                # 更新每一个合法窗口
                if e == 0 or i - l < e - b:
                    b, e = l, i + 1
                c = s[l]
                if c in needs:
                    needs[c] += 1
                    if needs[c] > 0:
                        missing += 1
                l += 1
        return s[b:e]


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().minWindow("acbbaca", "aba"))
