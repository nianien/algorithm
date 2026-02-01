# 283.move-zeroes
"""
Given an integer array nums, move all 0's to the end of it while maintaining 
the relative order of the non-zero elements. 

 Note that you must do this in-place without making a copy of the array. 

 
 Example 1: 
 Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
 
 Example 2: 
 Input: nums = [0]
Output: [0]
 
 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 -2³¹ <= nums[i] <= 2³¹ - 1 
 

 
Follow up: Could you minimize the total number of operations done?

 Related Topics Array Two Pointers 👍 18931 👎 568

"""
from typing import *


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def moveZeroes_v0(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        n = len(nums)
        i = 0 # next write position (from first zero)
        while i < n and nums[i] != 0:
            i += 1
        j = i + 1 # scan position
        # j is scan position
        while j < n:
            if nums[j] != 0:
                # invariant: nums[0:i) are non-zeros, nums[i:j) are zeros
                nums[i], nums[j] = nums[j], 0
                i += 1
            j += 1

    def moveZeroes(self, nums: List[int]) -> None:
        # i 为写入位置
        i = 0
        n = len(nums)
        # j 为读取位置, 当读取到非零元素,则与 i 位置置换
        for j in range(n):
            if nums[j] != 0:
                if i != j:
                    nums[i], nums[j] = nums[j], nums[i]
                i += 1


# leetcode submit region end(Prohibit modification and deletion)


# test from here
if __name__ == '__main__':
    print(Solution())
