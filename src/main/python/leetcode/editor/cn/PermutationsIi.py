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
        # 结果集合
        res = []
        # 校验数组，存储排列元素索引位置
        check = [-1] * len(nums)
        n = len(nums)

        # 根据校验数组和原始数组计算排列数组
        def calculate():
            perm = [0] * n
            for i in range(0, len(check)):
                # v表示在arr[i]在新数组的索引+1
                # 因此新数组索引位置v-1对应的元素就是arr[i]
                perm[check[i]] = nums[i]
            return perm

        def backtrack(idx):
            if idx == n:
                # 此时说明找到了一组
                return res.append(calculate())
            for i in range(0, n):
                # 为了解决重复元素的问题, 只需要保证在填第idx个元素的时候, 重复数字只会被填入一次即可
                # 也就是每次填入的数一定是这个数所在集合中「从左往右第一个未被填过的数字」
                # 也就是说, 相同的数字，只有左边的使用了，右边的才能使用，如果前一个相同元素未使用, 那么当前元素就不能使用
                # 这里举例说明，比如存在三个1，第二个1只有在使用2次的时候才能使用，第三个1只有在使用3次的时候才能使用
                if check[i] != -1 or i > 0 and check[i - 1] == -1 and nums[i] == nums[i - 1]:
                    continue
                # 记录元素在排列数组中的位置
                check[i] = idx
                # 递归
                backtrack(idx + 1)
                # 回溯, 重置索引位置
                check[i] = -1

        backtrack(0)
        return res

    # 该方法生成排列未按字典排序, 有序算法可参考golang实现
    def permuteUnique2(self, nums: List[int]) -> List[List[int]]:
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
    print(Solution().permuteUnique([1, 1, 3]))
    print(Solution().permuteUnique2([1, 1, 3]))
