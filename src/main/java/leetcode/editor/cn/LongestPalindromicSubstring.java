//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3350 👎 0


package leetcode.editor.cn;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        System.out.println(solution.longestPalindrome("abcba"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         *  a. i + 1 = j -1，即回文长度为1时，dp[ i ][ i ] = true;
         *  b. i +1 = (j - 1) -1，即回文长度为2时，dp[ i ][ i + 1] = （s[ i ] == s[ i + 1]）。
         * @param s
         * @return
         */
        public String longestPalindrome(String s) {
            int length = s.length();
//            boolean[][] dp=new boolean[s.length()][s.length()];
            // 按列为主,dp[i][j]=arr[j * (j + 1) / 2 + i]
            boolean[] arr = new boolean[length * (length + 1) / 2];
            int row = 0, col = 0;
            for (int i = s.length() - 2; i >= 0; i--) {
                for (int j = i + 1; j <= s.length() - 1; j++) {
//                    if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1])) {
                    if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || arr[(j * (j - 1) / 2 + i + 1)])) {
                        arr[j * (j + 1) / 2 + i] = true;
                        if (j - i + 1 > col - row + 1) {
                            row = i;
                            col = j;
                        }
                    }
                }
            }
            return s.substring(row, col + 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}