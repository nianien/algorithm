# 20.valid-parentheses
"""
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 determine if the input string is valid. 

 An input string is valid if: 

 
 Open brackets must be closed by the same type of brackets. 
 Open brackets must be closed in the correct order. 
 Every close bracket has a corresponding open bracket of the same type. 
 

 
 Example 1: 

 
 Input: s = "()" 
 

 Output: true 

 Example 2: 

 
 Input: s = "()[]{}" 
 

 Output: true 

 Example 3: 

 
 Input: s = "(]" 
 

 Output: false 

 Example 4: 

 
 Input: s = "([])" 
 

 Output: true 

 Example 5: 

 
 Input: s = "([)]" 
 

 Output: false 

 
 Constraints: 

 
 1 <= s.length <= 10⁴ 
 s consists of parentheses only '()[]{}'. 
 

 Related Topics String Stack 👍 27233 👎 1975

"""


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def isValid(self, s: str) -> bool:
        pm = {'(': ')', '{': '}', '[': ']'}
        stack = []
        for c in s:
            if c in pm:
                stack.append(c)
            else:
                if not stack:
                    return False
                pre = stack.pop()
                if pm[pre] != c:
                    return False
        return len(stack) == 0


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().isValid("()"))
    print(Solution().isValid("()[]{}"))
    print(Solution().isValid("([)]"))
