//190.颠倒给定的 32 位无符号整数的二进制位。
//
// 
//
// 提示： 
//
// 
// 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的
//还是无符号的，其内部的二进制表示形式都是相同的。 
// 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -10737418
//25。 
// 
//
// 
//
// 进阶: 
//如果多次调用这个函数，你将如何优化你的算法？ 
//
// 
//
// 示例 1： 
//
// 
//输入: 00000010100101000001111010011100
//输出: 00111001011110000010100101000000
//解释: 输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
//     因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。 
//
// 示例 2： 
//
// 
//输入：11111111111111111111111111111101
//输出：10111111111111111111111111111111
//解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
//     因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。 
//
// 示例 1： 
//
// 
//输入：n = 00000010100101000001111010011100
//输出：964176192 (00111001011110000010100101000000)
//解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
//     因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。 
//
// 示例 2： 
//
// 
//输入：n = 11111111111111111111111111111101
//输出：3221225471 (10111111111111111111111111111111)
//解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
//     因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。 
//
// 
//
// 提示： 
//
// 
// 输入是一个长度为 32 的二进制字符串 
// 
// Related Topics 位运算 
// 👍 369 👎 0


package leetcode.editor.cn;

public class ReverseBits {
    public static void main(String[] args) {
        Solution solution = new ReverseBits().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // you need treat n as an unsigned value
        private static final int M1 = 0x55555555; // 01010101010101010101010101010101
        private static final int M2 = 0x33333333; // 00110011001100110011001100110011
        private static final int M4 = 0x0f0f0f0f; // 00001111000011110000111100001111
        private static final int M8 = 0x00ff00ff; // 00000000111111110000000011111111

        public int reverseBits2(int n) {
            //n >>> 1 & M1表示偶数位的值,并且向低位移动,变为奇数位
            // (n & M1) << 1表示奇数位的值向高位移动,变为偶数位
            //整体表示低奇数位与高偶数位完成翻转
            n = n >>> 1 & M1 | (n & M1) << 1;
            //同理,低两位与高两位完成翻转
            n = n >>> 2 & M2 | (n & M2) << 2;
            //同理,低4位与高4位完成翻转
            n = n >>> 4 & M4 | (n & M4) << 4;
            //同理,低8位与高8位完成翻转
            n = n >>> 8 & M8 | (n & M8) << 8;
            //同理,低16位与高16位完成翻转
            //由于int型32位, 前16位和后16位内部完成翻转,然后将前后16位整体再翻转一次即可
            return n >>> 16 | n << 16;
        }

        public int reverseBits(int n) {
            //先二分翻转,然后递归翻转各自的部分
            //n >>> 16 高16位右移低16位
            //n << 16左移至高16位
            // 或运算完成翻转
            n = n >>> 16 | n << 16;
            //n >>> 8 & M8表示将高8位右移
            // (n & M8) << 8表示低8位左移
            // 或运算完成每8位的前后翻转
            n = n >>> 8 & M8 | (n & M8) << 8;
            //n >>> 4 & M4表示将高4位右移
            //(n & M4) << 4表示低4位左移
            // 或运算完成每4位的前后翻转
            n = n >>> 4 & M4 | (n & M4) << 4;
            //同理高2位与低两位的翻转
            n = n >>> 2 & M2 | (n & M2) << 2;
            //n >>> 1高偶数位右移至低奇数位
            //(n & M1) << 1 低奇数位左移至高偶数位
            //或运算完成高偶数位与低奇数位的翻转
            n = n >>> 1 & M1 | (n & M1) << 1;
            return n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}