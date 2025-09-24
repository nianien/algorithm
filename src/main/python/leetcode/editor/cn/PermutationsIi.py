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
        # 存储所有不重复的排列结果
        res = []
        # check[i] 记录 nums[i] 在最终排列中的位置索引
        # -1 表示该元素还未被使用，非-1值表示在排列中的位置
        check = [-1] * len(nums)
        n = len(nums)
        # 关键：先排序使相同元素相邻，才能正确使用剪枝条件
        nums.sort()

        # 根据check数组的位置映射，构造最终的排列
        def calculate():
            perm = [0] * n
            for i in range(0, len(check)):
                # check[i] 是 nums[i] 在排列中的位置
                # 将 nums[i] 放到 perm[check[i]] 位置
                perm[check[i]] = nums[i]
            return perm

        def backtrack(idx):
            # 已填满所有位置，找到一个完整排列
            if idx == n:
                return res.append(calculate())
            # 尝试将每个元素放到第idx个位置
            for i in range(0, n):
                # 为了解决重复元素的问题, 只需要保证重复数字只会被填入一次即可
                # 也就是每次填入的数一定是这个数所在集合中「从左往右第一个未被填过的数字」
                # 也就是说, 相同的数字，只有左边的使用了，右边的才能使用，如果前一个相同元素未使用, 那么当前元素就不能使用
                # 剪枝条件1：该元素已被使用
                # 剪枝条件2：跳过重复元素，确保相同元素按顺序使用
                # 例如：[1,1,2] 中，只有第一个1被使用后，第二个1才能被使用
                if check[i] != -1 or i > 0 and check[i - 1] == -1 and nums[i] == nums[i - 1]:
                    continue
                
                # 标记：将 nums[i] 放到第 idx 个位置
                check[i] = idx
                # 递归：继续填下一个位置
                backtrack(idx + 1)
                # 回溯：恢复状态，尝试其他选择
                check[i] = -1

        backtrack(0)
        return res

    # 方法2：通过交换元素生成排列（未按字典序排序）
    def permuteUnique2(self, nums: List[int]) -> List[List[int]]:
        def backtrack(begin=0):
            # 已处理完所有位置
            if begin == n - 1:
                res.append(nums[:])
            else:
                # 尝试将每个元素交换到begin位置
                for i in range(begin, n):
                    # 剪枝：只使用每个重复元素的第一个
                    if nums.index(nums[i], begin) == i:
                        # 交换元素
                        nums[begin], nums[i] = nums[i], nums[begin]
                        # 递归处理下一个位置
                        backtrack(begin + 1)
                        # 回溯：恢复交换
                        nums[begin], nums[i] = nums[i], nums[begin]

        res = []
        n = len(nums)
        backtrack(0)
        return res


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().permuteUnique([1, 1, 3]))
    print(Solution().permuteUnique2([1, 1, 3]))
