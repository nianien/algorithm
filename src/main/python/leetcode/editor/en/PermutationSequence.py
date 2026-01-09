# 60.permutation-sequence
"""
The set [1, 2, 3, ..., n] contains a total of n! unique permutations. 

 By listing and labeling all of the permutations in order, we get the following 
sequence for n = 3: 

 
 "123" 
 "132" 
 "213" 
 "231" 
 "312" 
 "321" 
 

 Given n and k, return the kᵗʰ permutation sequence. 

 
 Example 1: 
 Input: n = 3, k = 3
Output: "213"
 
 Example 2: 
 Input: n = 4, k = 9
Output: "2314"
 
 Example 3: 
 Input: n = 3, k = 1
Output: "123"
 
 
 Constraints: 

 
 1 <= n <= 9 
 1 <= k <= n! 
 

 Related Topics Math Recursion 👍 7171 👎 501

"""
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:

    def getPermutation(self, n: int, k: int) -> str:
        # nums 保持有序：用于字典序定位
        nums: List[int] = list(range(1, n + 1))

        # 预计算阶乘 fact[i] = i!
        fact = [1] * (n + 1)
        for i in range(1, n + 1):
            fact[i] = fact[i - 1] * i

        # k 转成 0-based，方便用整除/取模做分块
        k -= 1

        res: List[str] = []
        # 逐位确定：剩余 i 个数时，每个前缀块大小是 (i-1)!
        for i in range(n, 0, -1):
            block = fact[i - 1]
            idx = k // block
            res.append(str(nums[idx]))
            nums.pop(idx)  # 移除已用数字
            k %= block  # 更新为当前块内的偏移

        return "".join(res)

    def getPermutation_v0(self, n: int, k: int) -> str:

        # 根据校验数组和原始数组计算排列数组
        def calculate(res):
            res[0] += 1
            if res[0] == k:
                perm = [0] * n
                for i in range(0, len(check)):
                    perm[check[i]] = i + 1
                return "".join(str(x) for x in perm)

        def backtrack(idx, res):
            if idx == n:
                p = calculate(res)
                if p:
                    return p
                return None
            for i in range(0, n):
                # 该元素已经使用
                if check[i] != -1:
                    continue
                # 记录元素在排列数组中的位置
                check[i] = idx
                # 递归
                p = backtrack(idx + 1, res)
                # 回溯, 重置索引位置
                check[i] = -1
                if p:
                    return p

        # 校验数组，存储排列元素索引位置
        check = [-1] * n
        return backtrack(0, [0])


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().getPermutation(3, 3))
