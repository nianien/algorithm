# 53.maximum-subarray
"""
Given an integer array nums, find the subarray with the largest sum, and return 
its sum. 

 
 Example 1: 

 
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 

 Example 2: 

 
Input: nums = [1]
Output: 1
Explanation: The subarray [1] has the largest sum 1.
 

 Example 3: 

 
Input: nums = [5,4,-1,7,8]
Output: 23
Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 -10⁴ <= nums[i] <= 10⁴ 
 

 
 Follow up: If you have figured out the O(n) solution, try coding another 
solution using the divide and conquer approach, which is more subtle. 

 Related Topics Array Divide and Conquer Dynamic Programming 👍 37180 👎 1578

"""
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        if len(nums)==0:
            return 0
        cur,res=nums[0],nums[0]
        for v in nums[1:]:
            cur=max(v,cur+v)
            res=max(cur,res)
        return res



# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().maxSubArray([5, 4, -1, 7, 8]))
    print(Solution().maxSubArray([-3, -2, -5]))
    print(Solution().maxSubArray([-2, 1, -3, 4, -1, 2, 1, -5, 4]))
