//给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。 
//
// 返回被除数 dividend 除以除数 divisor 得到的商。 
//
// 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 
//
// 
//
// 示例 1: 
//
// 输入: dividend = 10, divisor = 3
//输出: 3
//解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 
//
// 示例 2: 
//
// 输入: dividend = 7, divisor = -3
//输出: -2
//解释: 7/-3 = truncate(-2.33333..) = -2 
//
// 
//
// 提示： 
//
// 
// 被除数和除数均为 32 位有符号整数。 
// 除数不为 0。 
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。 
// 
// Related Topics 数学 二分查找 
// 👍 527 👎 0


package leetcode.editor.cn;

public class DivideTwoIntegers {
    public static void main(String[] args) {
        Solution solution = new DivideTwoIntegers().new Solution();
        System.out.println(solution.divide(-2147483648, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int divide(int dividend, int divisor) {
            if (dividend == 0) {
                return 0;
            }
            if (dividend == Integer.MIN_VALUE && divisor == -1) {
                return Integer.MAX_VALUE;
            }
            long dvd = dividend; //被除数
            long dvs = divisor;  //除数
            int sign = (dvd ^ dvs) < 0 ? -1 : 1;
            dvd = Math.abs(dvd);
            dvs = Math.abs(dvs);
            int res = 0;  //商

            //当被除数大于等于除数时
//            while (dvd >= dvs) {
//                int i = 0;
//                //2^n*dvs<=dvd
//                while ((dvs << i) <= dvd) {
//                    i++;
//                }
//                res += (1 << (i - 1));
//                dvd -= (dvs << (i - 1));
//            }
            for (int i = 31; i >= 0; i--) {
                if ((dvs << i) <= dvd) {//找出足够大的数2^n*divisor
                    res += 1 << i;//将结果加上2^n
                    dvd -= dvs << i;//将被除数减去2^n*divisor
                }
            }
            return sign > 0 ? res : -res;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}