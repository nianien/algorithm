# 46.permutations
# 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [1,2,3]
# 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
#  
# 
#  示例 2： 
# 
#  
# 输入：nums = [0,1]
# 输出：[[0,1],[1,0]]
#  
# 
#  示例 3： 
# 
#  
# 输入：nums = [1]
# 输出：[[1]]
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 6 
#  -10 <= nums[i] <= 10 
#  nums 中的所有整数 互不相同 
#  
#  Related Topics 回溯算法 
#  👍 1392 👎 0

from typing import List


# leetcode submit region begin(Prohibit modification and deletion)


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """

        def backtrack(begin: int):
            # 所有数都填完了
            if begin == n - 1:
                res.append(nums[:])
                return 
            for i in range(begin, n):
                # 动态维护数组
                nums[begin], nums[i] = nums[i], nums[begin]
                # 继续递归填下一个数
                backtrack(begin + 1)
                # 撤销操作
                nums[begin], nums[i] = nums[i], nums[begin]

        n = len(nums)
        res = []
        backtrack(0)
        return res


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution())
