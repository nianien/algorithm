# 47.permutations-ii
# 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [1,1,2]
# 输出：
# [[1,1,2],
#  [1,2,1],
#  [2,1,1]]
#  
# 
#  示例 2： 
# 
#  
# 输入：nums = [1,2,3]
# 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 8 
#  -10 <= nums[i] <= 10 
#  
#  Related Topics 回溯算法 
#  👍 711 👎 0

from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        def backtrack(begin=0):
            if begin == n - 1:
                res.append(nums[:])
            else:
                for i in range(begin, n):
                    # 从begin开始,num[i]第一次参与排排列
                    if nums.index(nums[i], begin) == i:
                        nums[begin], nums[i] = nums[i], nums[begin]
                        backtrack(begin + 1)
                        nums[begin], nums[i] = nums[i], nums[begin]
        res = []
        n = len(nums)
        backtrack(0)
        return res


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution())
