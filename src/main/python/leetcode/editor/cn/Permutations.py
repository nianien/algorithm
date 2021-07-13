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
                return res.append(calculate())
            for i in range(0, n):
                # 该元素已经使用
                if check[i] != -1:
                    continue
                # 记录元素在排列数组中的位置
                check[i] = idx
                # 递归
                backtrack(idx + 1)
                # 回溯, 重置索引位置
                check[i] = -1

        # 结果集合
        res = []
        # 校验数组，存储排列元素索引位置
        check = [-1] * len(nums)
        n = len(nums)
        backtrack(0)
        return res

    # 该方法生成排列未按字典排序, 有序算法可参考golang实现
    def permute2(self, nums: List[int]) -> List[List[int]]:
        res = []
        n = len(nums)

        def backtrack(begin):
            # 所有数都填完了
            if begin == n - 1:
                res.append(nums[:])
                return
            for i in range(begin, n):
                self.move_head(nums, begin, i)
                # 继续递归填下一个数
                backtrack(begin + 1)
                # 撤销操作
                self.move_tail(nums, begin, i)

        backtrack(0)
        return res

    # high元素插入到low元素的前面
    def move_head(self, arr, low, high):
        if low == high:
            return
        if low > high:
            self.move_head(arr, high, low)
            return
        temp_ = arr[high]
        for i in range(high, low, -1):
            arr[i] = arr[i - 1]
        arr[low] = temp_

    # 将low位置的元素插入到high的元素后面
    def move_tail(self, arr, low, high):
        if low == high:
            return
        if low > high:
            self.moveTail(arr, high, low)
            return
        temp_ = arr[low]
        for i in range(low, high):
            arr[i] = arr[i + 1]
        arr[high] = temp_


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().permute([1, 2, 3]))
    print(Solution().permute2([1, 2, 3]))
