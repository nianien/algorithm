# 49.group-anagrams
"""
Given an array of strings strs, group the anagrams together. You can return the 
answer in any order. 

 
 Example 1: 

 
 Input: strs = ["eat","tea","tan","ate","nat","bat"] 
 

 Output: [["bat"],["nat","tan"],["ate","eat","tea"]] 

 Explanation: 

 
 There is no string in strs that can be rearranged to form "bat". 
 The strings "nat" and "tan" are anagrams as they can be rearranged to form 
each other. 
 The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to 
form each other. 
 

 Example 2: 

 
 Input: strs = [""] 
 

 Output: [[""]] 

 Example 3: 

 
 Input: strs = ["a"] 
 

 Output: [["a"]] 

 
 Constraints: 

 
 1 <= strs.length <= 10⁴ 
 0 <= strs[i].length <= 100 
 strs[i] consists of lowercase English letters. 
 

 Related Topics Array Hash Table String Sorting 👍 21635 👎 742

"""
from collections import defaultdict
from typing import *


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        def key(s: str):
            arr = [0] * 26
            for c in s:
                # cannot use like java: c-'a'
                arr[ord(c) - ord('a')] += 1
            return tuple(arr)

        m = defaultdict(list)
        for s in strs:
            m[key(s)].append(s)
        return list(m.values())


# leetcode submit region end(Prohibit modification and deletion)


# test from here
if __name__ == '__main__':
    print(Solution())
