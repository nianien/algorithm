# 28.find-the-index-of-the-first-occurrence-in-a-string
"""
Given two strings needle and haystack, return the index of the first occurrence 
of needle in haystack, or -1 if needle is not part of haystack. 

 
 Example 1: 

 
Input: haystack = "sadbutsad", needle = "sad"
Output: 0
Explanation: "sad" occurs at index 0 and 6.
The first occurrence is at index 0, so we return 0.
 

 Example 2: 

 
Input: haystack = "leetcode", needle = "leeto"
Output: -1
Explanation: "leeto" did not occur in "leetcode", so we return -1.
 

 
 Constraints: 

 
 1 <= haystack.length, needle.length <= 10⁴ 
 haystack and needle consist of only lowercase English characters. 
 

 Related Topics Two Pointers String String Matching 👍 7363 👎 558

"""


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def strStr_v0(self, haystack: str, needle: str) -> int:
        if needle == "":
            return 0
        n, m = len(haystack), len(needle)
        for i in range(n - m + 1):
            for j in range(m):
                if haystack[i + j] != needle[j]:
                    break
            else:
                return i
        else:
            return -1

    def strStr(self, haystack: str, needle: str) -> int:
        """
        Sunday (Quick Search):
        On mismatch, look at the character right after the current window and shift
        the window so that the last occurrence of that character in needle aligns with it.
        """
        n, m = len(haystack), len(needle)
        if m == 0:
            return 0
        if m > n:
            return -1
        # last[ch] = ch 在 needle 中最后一次出现的位置
        # last = {'a':3, 'b':1, 'c':2, 'd':4}
        last = {}
        for i, ch in enumerate(needle):
            last[ch] = i
        i = 0
        while i <= n - m:
            # 尝试匹配当前窗口
            # haystack:    [ i ...... i+m-1 ]
            # needle:      [ 0 ...... m-1   ]
            for j in range(m):
                if haystack[i + j] != needle[j]:
                    break
            else:
                return i  # 整个 needle 匹配成功
            # 关键：看窗口右边那个字符
            # haystack:    [ window ][ ch ]
            nxt = i + m
            if nxt >= n:
                return -1
            ch = haystack[nxt]
            # 位移规则（核心）
            # 1) ch 不在 needle:
            #    直接跳过整个窗口 + 1
            #    haystack: [ window ][ x ]
            #    needle:   a b c a d
            #    shift = m + 1
            # 2) ch 在 needle:
            #    对齐 needle 中 ch 的【最后一次出现】
            #    haystack:        ? ? ? a
            #    needle:          a b c a d
            #                         ^
            #    shift = m - last[ch]
            i += m - last.get(ch, -1)

        return -1


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().strStr("hello", "ll"))
