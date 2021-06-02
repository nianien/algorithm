# 152.maximum-product-subarray
# 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。 
# 
#  
# 
#  示例 1: 
# 
#  输入: [2,3,-2,4]
# 输出: 6
# 解释: 子数组 [2,3] 有最大乘积 6。
#  
# 
#  示例 2: 
# 
#  输入: [-2,0,-1]
# 输出: 0
# 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
#  Related Topics 数组 动态规划 
#  👍 1123 👎 0
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        min_, max_, ans = nums[0], nums[0], nums[0]
        for i in range(1, len(nums)):
            mx = max_
            mn = min_
            # 求最大值, 若nums[i]>0则: mx * nums[i],否则mn * nums[i]
            max_ = max(mx * nums[i], nums[i], mn * nums[i]);
            # 求最小值, 若nums[i]>0则: min * nums[i],否则max * nums[i]
            min_ = min(mn * nums[i], nums[i], mx * nums[i]);
            ans = max(max_, ans)
        return ans


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    # print(Solution().maxProduct([2, 3, -2, 4]))
    print(Solution().maxProduct([2, -5, -2, -4, 3]))
