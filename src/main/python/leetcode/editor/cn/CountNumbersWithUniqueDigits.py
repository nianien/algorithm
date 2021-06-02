# 357.count-numbers-with-unique-digits
# 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。 
# 
#  示例: 
# 
#  输入: 2
# 输出: 91 
# 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
#  
#  Related Topics 数学 动态规划 回溯算法 
#  👍 138 👎 0

# leetcode submit region begin(Prohibit modification and deletion)


class Solution:
    def countNumbersWithUniqueDigits(self, n: int) -> int:
        # 实际上是求排列组合C(10,n)
        # f(n)= 9*P(9,n-1)+f(n-1)
        # n为0时,返回1
        if n <= 0:
            return 1
        ans = 1
        # 排列数,高位有9个选择
        perm = 9
        # 当前位数字排列数
        rax = 9
        for i in range(1, n + 1):
            # 从次位开始, 数字可能性依次递减
            if i > 1:
                perm *= rax
                rax -= 1
            ans += perm
        return ans


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    # print(Solution().countNumbersWithUniqueDigits(0))
    # print(Solution().countNumbersWithUniqueDigits(1))
    # print(Solution().countNumbersWithUniqueDigits(2))
    print(Solution().countNumbersWithUniqueDigits(3))
