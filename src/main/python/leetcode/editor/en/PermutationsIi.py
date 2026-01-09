# 47.permutations-ii
"""
Given a collection of numbers, nums, that might contain duplicates, return all 
possible unique permutations in any order. 

 
 Example 1: 

 
Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
 

 Example 2: 

 
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 

 
 Constraints: 

 
 1 <= nums.length <= 8 
 -10 <= nums[i] <= 10 
 

 Related Topics Array Backtracking Sorting 👍 9045 👎 160

"""
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        used = [False] * len(nums)
        path = []
        res = []

        def dfs():
            if len(path) == len(nums):
                res.append(path[:])
                return
            for i in range(len(nums)):
                if used[i] or (i > 0 and nums[i] == nums[i - 1] and not used[i - 1]):
                    continue
                path.append(nums[i])
                used[i] = True
                dfs()
                used[i] = False
                path.pop()

        dfs()
        return res


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().permuteUnique([1, 1, 2]))
