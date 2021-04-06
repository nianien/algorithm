//输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。 
//
// 示例 1: 
//
// 输入: n = 1
//输出: [1,2,3,4,5,6,7,8,9]
// 
//
// 
//
// 说明： 
//
// 
// 用返回一个整数列表来代替打印 
// n 为正整数 
// 
// Related Topics 数学 
// 👍 102 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class DaYinCong1daoZuiDaDeNweiShuLcof {
    public static void main(String[] args) {
        Solution solution = new DaYinCong1daoZuiDaDeNweiShuLcof().new Solution();
        System.out.println(TypeDefined.toList(solution.printNumbers(1)));
        System.out.println(solution.printNumbers2(2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] printNumbers(int n) {
            int max = (int) Math.pow(10, n) - 1;
            int[] arr = new int[max];
            for (int i = 0; i < max; i++) {
                arr[i] = i + 1;
            }
            return arr;
        }


        private List<String> printNumbers2(int n) {
            List<String> numbers = new ArrayList<>();
            char[] arr = new char[n];
            for (int i = 0; i < n; i++) {
                arr[i] = '0';
            }
            out:
            for (; ; ) {
                while (arr[0] < '9') {
                    arr[0]++;
                    numbers.add(toNumber(arr));
                }
                for (int i = 1; i < n; i++) {
                    if (arr[i] < '9') {
                        arr[i]++;
                        break;
                    } else if (i == n - 1) {
                        break out;
                    } else {
                        arr[i] = '0';
                    }
                }
                arr[0] = '0';
                numbers.add(toNumber(arr));
            }
            return numbers;
        }

        private String toNumber(char[] arr) {
           /* StringBuilder s = new StringBuilder();
            int n = arr.length;
            boolean validZero = false;
            for (n--; n >= 0; n--) {
                if (arr[n] != '0') {
                    validZero = true;
                }
                if (valid || arr[n] != '0') {
                    s.append(arr[n]);
                }
            }
            return s.toString();*/
            int sum = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                sum = sum * 10 + (arr[i] - '0');
            }
            return sum + "";
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}