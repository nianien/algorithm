# 46.permutations
"""
Given an array nums of distinct integers, return all the possible permutations. 
You can return the answer in any order. 

 
 Example 1: 
 Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 
 Example 2: 
 Input: nums = [0,1]
Output: [[0,1],[1,0]]
 
 Example 3: 
 Input: nums = [1]
Output: [[1]]
 
 
 Constraints: 

 
 1 <= nums.length <= 6 
 -10 <= nums[i] <= 10 
 All the integers of nums are unique. 
 

 Related Topics Array Backtracking 👍 20604 👎 378

"""
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        used = [False]*len(nums)
        path = []
        res = []

        def dfs():
            if len(path) == len(nums):
                res.append(path[:])
                return
            for i in range(len(nums)):
                if used[i]:
                    continue
                used[i] = True
                path.append(nums[i])
                dfs()
                path.pop()
                used[i] = False

        dfs()
        return res


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().permute([1,2,3]))
