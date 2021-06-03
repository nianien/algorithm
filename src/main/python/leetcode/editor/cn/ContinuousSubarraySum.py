# 523.continuous-subarray-sum
# 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组： 
# 
#  
#  子数组大小 至少为 2 ，且 
#  子数组元素总和为 k 的倍数。 
#  
# 
#  如果存在，返回 true ；否则，返回 false 。 
# 
#  如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：nums = [23,2,4,6,7], k = 6
# 输出：true
# 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。 
# 
#  示例 2： 
# 
#  
# 输入：nums = [23,2,6,4,7], k = 6
# 输出：true
# 解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
# 42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
#  
# 
#  示例 3： 
# 
#  
# 输入：nums = [23,2,6,4,7], k = 13
# 输出：false
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= nums.length <= 105 
#  0 <= nums[i] <= 109 
#  0 <= sum(nums[i]) <= 231 - 1 
#  1 <= k <= 231 - 1 
#  
#  Related Topics 数学 动态规划 
#  👍 336 👎 0

from collections import defaultdict
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def checkSubarraySum(self, nums: List[int], k: int) -> bool:
        """
         前缀和以及同余定理, 如果presum[i-1]%k==presum[i]%k, 则必包含存在一个包含num[i]的子数组,使其和是K的倍数。
         记录每个余数与索引的位置，当发现同余元素时，则索引位置到当前元素的和为K的整数倍
        :param nums:
        :param k:
        :return:
        """
        n = len(nums)
        idx_remainder = defaultdict()
        remainder = 0
        for i in range(n):
            remainder = (remainder + nums[i]) % k
            if remainder == 0 and i > 0:
                # 如果前缀和被K整除即返回,因此表明从数组开始到当前位置和第一次满足条件
                print(nums[:i + 1])
                return True
            if remainder in idx_remainder:
                pre_idx = idx_remainder[remainder]
                if i - pre_idx > 1:
                    # 打印满足条件的序列
                    print(nums[pre_idx:i + 1])
                    return True
            else:
                idx_remainder[remainder] = i
        return False


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    # print(Solution().checkSubarraySum([23, 2, 4, 6, 7], 13))
    # print(Solution().checkSubarraySum([23, 2, 6, 4, 7], 13))
    print(Solution().checkSubarraySum([23, 2, 4, 6, 6, 7, 8], 7))
    print(Solution().checkSubarraySum([7, 7, 8, 9], 7))
