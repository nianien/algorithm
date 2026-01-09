# 238.product-of-array-except-self
"""
Given an integer array nums, return an array answer such that answer[i] is 
equal to the product of all the elements of nums except nums[i]. 

 The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit 
integer. 

 You must write an algorithm that runs in O(n) time and without using the 
division operation. 

 
 Example 1: 
 Input: nums = [1,2,3,4]
Output: [24,12,8,6]
 
 Example 2: 
 Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 
 
 Constraints: 

 
 2 <= nums.length <= 10⁵ 
 -30 <= nums[i] <= 30 
 The input is generated such that answer[i] is guaranteed to fit in a 32-bit 
integer. 
 

 
 Follow up: Can you solve the problem in O(1) extra space complexity? (The 
output array does not count as extra space for space complexity analysis.) 

 Related Topics Array Prefix Sum 👍 25388 👎 1656

"""
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        res = [1] * len(nums)
        left = 1
        right = 1
        for i in range(len(nums)):
            res[i] *= left
            left *= nums[i]
        for i in range(len(nums) - 1, -1, -1):
            res[i] *= right
            right *= nums[i]
        return res


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().productExceptSelf([1,2,3,4]))
