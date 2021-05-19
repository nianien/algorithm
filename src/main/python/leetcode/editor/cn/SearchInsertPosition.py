# 35.search-insert-position
# 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
# 
#  你可以假设数组中无重复元素。 
# 
#  示例 1: 
# 
#  输入: [1,3,5,6], 5
# 输出: 2
#  
# 
#  示例 2: 
# 
#  输入: [1,3,5,6], 2
# 输出: 1
#  
# 
#  示例 3: 
# 
#  输入: [1,3,5,6], 7
# 输出: 4
#  
# 
#  示例 4: 
# 
#  输入: [1,3,5,6], 0
# 输出: 0
#  
#  Related Topics 数组 二分查找 
#  👍 912 👎 0

# leetcode submit region begin(Prohibit modification and deletion)
class Solution(object):
    def searchInsert(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        return self._searchInsert(nums, target, 0, len(nums) - 1)

    def _searchInsert(self, nums, target, begin, end):
        if begin > end:
            return begin
        mid = (begin + end) // 2
        if nums[mid] == target:
            return mid
        elif nums[mid] > target:
            return self._searchInsert(nums, target, begin, mid - 1)
        else:
            return self._searchInsert(nums, target, mid + 1, end)


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().searchInsert([1, 2, 3, 4, 5, 7, 8], 6))
