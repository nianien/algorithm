# 560.subarray-sum-equals-k
"""
Given an array of integers nums and an integer k, return the total number of 
subarrays whose sum equals to k. 

 A subarray is a contiguous non-empty sequence of elements within an array. 

 
 Example 1: 
 Input: nums = [1,1,1], k = 2
Output: 2
 
 Example 2: 
 Input: nums = [1,2,3], k = 3
Output: 2
 
 
 Constraints: 

 
 1 <= nums.length <= 2 * 10⁴ 
 -1000 <= nums[i] <= 1000 
 -10⁷ <= k <= 10⁷ 
 

 Related Topics Array Hash Table Prefix Sum 👍 24496 👎 824

"""
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        prefix = 0
        cnt = 0
        # freq[s] = “前缀和等于 s 的历史位置，一共有多少个”
        feq = {0: 1}
        for v in nums:
            prefix += v
            #获取前缀和∑[1,....x]=pre-k的次数
            #已知∑[1,....x]=pre-k,∑[1,....i]=pre, 则∑[x...i]=k
            cnt += feq.get(prefix - k, 0)
            feq[prefix]=feq.get(prefix,0)+1
        return cnt


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().subarraySum([1], 0))
    print(Solution().subarraySum([1, 1, 1], 2))
    print(Solution().subarraySum([1, 2, 3], 3))
