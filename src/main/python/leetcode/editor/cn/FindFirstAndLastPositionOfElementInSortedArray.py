# 34.find-first-and-last-position-of-element-in-sorted-array
# 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
# 
#  如果数组中不存在目标值 target，返回 [-1, -1]。 
# 
#  进阶： 
# 
#  
#  你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
#  
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [5,7,7,8,8,10], target = 8
# 输出：[3,4] 
# 
#  示例 2： 
# 
#  
# 输入：nums = [5,7,7,8,8,10], target = 6
# 输出：[-1,-1] 
# 
#  示例 3： 
# 
#  
# 输入：nums = [], target = 0
# 输出：[-1,-1] 
# 
#  
# 
#  提示： 
# 
#  
#  0 <= nums.length <= 105 
#  -109 <= nums[i] <= 109 
#  nums 是一个非递减数组 
#  -109 <= target <= 109 
#  
#  Related Topics 数组 二分查找 
#  👍 1045 👎 0
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        ans = [-1, -1]
        self.searchRange0(nums, target, 0, len(nums) - 1, ans)
        return ans

    def searchRange0(self, nums: List[int], target: int, begin: int, end: int, ans: List[int]):
        if begin > end:
            return
        mid = (begin + end) // 2
        if nums[mid] < target:
            self.searchRange0(nums, target, mid + 1, end, ans)
        elif nums[mid] > target:
            self.searchRange0(nums, target, begin, mid - 1, ans)
        else:
            if ans[0] == -1 or ans[0] > mid:
                ans[0] = mid
                # 如果和前一个元素相等,则需要继续查找左边界
                if mid > 0 and nums[mid] == nums[mid - 1]:
                    self.searchRange0(nums, target, begin, mid - 1, ans)
            if ans[1] == -1 or ans[1] < mid:
                ans[1] = mid
                # 如果和后一个元素相等,则需要继续查找右边界
                if mid < len(nums) - 1 and nums[mid] == nums[mid + 1]:
                    self.searchRange0(nums, target, mid + 1, end, ans)


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    # print(Solution().searchRange([5, 7, 7, 8, 8, 10], 8))
    print(Solution().searchRange([3, 3, 3], 3))
