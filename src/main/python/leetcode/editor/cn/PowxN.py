# 50.powx-n
# 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：x = 2.00000, n = 10
# 输出：1024.00000
#  
# 
#  示例 2： 
# 
#  
# 输入：x = 2.10000, n = 3
# 输出：9.26100
#  
# 
#  示例 3： 
# 
#  
# 输入：x = 2.00000, n = -2
# 输出：0.25000
# 解释：2-2 = 1/22 = 1/4 = 0.25
#  
# 
#  
# 
#  提示： 
# 
#  
#  -100.0 < x < 100.0 
#  -231 <= n <= 231-1 
#  -104 <= xn <= 104 
#  
#  Related Topics 数学 二分查找 
#  👍 647 👎 0

# leetcode submit region begin(Prohibit modification and deletion)
class Solution(object):
    def myPow(self, x, n):
        """
        :type x: float
        :type n: int
        :rtype: float
        """
        return self.quickMul(x, n) if n >= 0 else 1.0 / self.quickMul(x, -n)

    def quickMul(self, x, n):
        """

        :param x:
        :param n:
        :return:
        """
        # n= (2^i0) + (2^i1)+ (2^i2) + ... + (2^ik)
        # x^n=x^(2^i0) * x^(2^i1) * x^(2^i2) * ... * x^(2^ik)
        ans = 1.0
        # 贡献的初始值为 x
        x_contribute = x
        # 在对 N 进行二进制拆分的同时计算答案
        while n > 0:
            if n % 2 == 1:
                # 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute
            # 将贡献不断地平方
            x_contribute *= x_contribute
            # 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            n //= 2
        return ans


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().myPow(0.00001, 2147483647))
