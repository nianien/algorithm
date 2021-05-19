# 43.multiply-strings
# 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。 
# 
#  示例 1: 
# 
#  输入: num1 = "2", num2 = "3"
# 输出: "6" 
# 
#  示例 2: 
# 
#  输入: num1 = "123", num2 = "456"
# 输出: "56088" 
# 
#  说明： 
# 
#  
#  num1 和 num2 的长度小于110。 
#  num1 和 num2 只包含数字 0-9。 
#  num1 和 num2 均不以零开头，除非是数字 0 本身。 
#  不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。 
#  
#  Related Topics 数学 字符串 
#  👍 638 👎 0

# leetcode submit region begin(Prohibit modification and deletion)
class Solution(object):
    def multiply(self, num1, num2):
        """
        :type num1: str
        :type num2: str
        :rtype: str
        """
        return "".join(str(i) for i in self._multiply_arr(num1[::-1], num2[::-1]))

    def _multiply_arr(self, num1, num2):
        """
        数组相乘,每个数组是单个数字
        :param num1:
        :param num2:
        :return:
        """
        len1 = len(num1)
        len2 = len(num2)
        len_ = len1 + len2
        ret = [0] * len_
        for i in range(len1):
            for j in range(len2):
                ret[i + j] += (int(num1[i]) * int(num2[j]))
        mod = 0
        for i in range(len_):
            mod += int(ret[i])
            ret[i] = mod % 10
            mod = mod // 10
        if mod > 0:
            ret[-1] = mod
        return self._remove_zero(ret)

    def _remove_zero(self, arr_):
        """
        移除高位0
        :param arr_:
        :return:
        """
        # last no-zero
        last = 0
        for i in range(len(arr_) - 1, -1, -1):
            if arr_[i] != 0:
                last = i
                break
        # ret[last - len_:-len_ - 1:-1])
        return arr_[0:last + 1][::-1]


# leetcode submit region end(Prohibit modification and deletion)

# test from here


if __name__ == '__main__':
    print(Solution().multiply("33", "27"))
print(Solution().multiply("9", "9"))
print(Solution().multiply("2", "3"))
print(Solution().multiply("999", "0"))
arr = [1, 2, 3, 4, 5]
la = 2
le = len(arr)
print(arr[la - le:-le - 1:-1])
print(arr[0:la + 1][::-1])
