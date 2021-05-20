# 67.add-binary
# 给你两个二进制字符串，返回它们的和（用二进制表示）。 
# 
#  输入为 非空 字符串且只包含数字 1 和 0。 
# 
#  
# 
#  示例 1: 
# 
#  输入: a = "11", b = "1"
# 输出: "100" 
# 
#  示例 2: 
# 
#  输入: a = "1010", b = "1011"
# 输出: "10101" 
# 
#  
# 
#  提示： 
# 
#  
#  每个字符串仅由字符 '0' 或 '1' 组成。 
#  1 <= a.length, b.length <= 10^4 
#  字符串如果不是 "0" ，就都不含前导零。 
#  
#  Related Topics 数学 字符串 
#  👍 611 👎 0

# leetcode submit region begin(Prohibit modification and deletion)
class Solution(object):
    def addBinary(self, a, b):
        """
        :type a: str
        :type b: str
        :rtype: str
        """
        a, b = a[::-1], b[::-1]
        la, lb = len(a), len(b)
        lm = max(la, lb)
        ret = [0] * lm
        mod = 0
        for i in range(lm):
            mod += (int(a[i]) if i < la else 0)
            mod += (int(b[i]) if i < lb else 0)
            # mod % 2
            ret[i] = mod & 1
            # mod // 2
            mod = mod >> 1
        if mod > 0:
            ret.append(mod)
        return "".join([str(i) for i in ret[::-1]])


# leetcode submit region end(Prohibit modification and deletion)


# test from here
if __name__ == '__main__':
    print(Solution().addBinary("1111", "1111"))
    print(Solution().addBinary("11", "11"))
