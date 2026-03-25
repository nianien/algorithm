//72.edit-distance
/**
 * Given two strings word1 and word2, return the minimum number of operations
 * required to convert word1 to word2.
 * <p>
 * You have the following three operations permitted on a word:
 * <p>
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 * <p>
 * <p>
 * Related Topics String Dynamic Programming 👍 16370 👎 324
 */

package leetcode.editor.en;

public class EditDistance {
    public static void main(String[] args) {
        Solution solution = new EditDistance().new Solution();
    }

    /**
     * 解题思路：动态规划 (Bottom-Up DP)
     *
     * 经典的编辑距离问题（Levenshtein Distance），将 word1 转换为 word2 的最少操作次数。
     * 允许三种操作：插入、删除、替换。
     *
     * 状态定义：
     *   dp[i][j] = word1 的前 i 个字符转换为 word2 的前 j 个字符所需的最少操作数
     *
     * 状态转移：
     *   1. word1[i-1] == word2[j-1]（当前字符相同，无需操作）：
     *      dp[i][j] = dp[i-1][j-1]
     *
     *   2. word1[i-1] != word2[j-1]（当前字符不同，取三种操作的最小值 +1）：
     *      dp[i][j] = min(
     *          dp[i-1][j],     // 删除 word1[i-1]
     *          dp[i][j-1],     // 在 word1 中插入 word2[j-1]
     *          dp[i-1][j-1]    // 将 word1[i-1] 替换为 word2[j-1]
     *      ) + 1
     *
     * 边界条件：
     *   dp[i][0] = i  （word2 为空，需删除 word1 的全部 i 个字符）
     *   dp[0][j] = j  （word1 为空，需插入 word2 的全部 j 个字符）
     *
     * 示例推演 word1="horse", word2="ros"：
     *       ""  r  o  s
     *   ""   0  1  2  3
     *   h    1  1  2  3
     *   o    2  2  1  2
     *   r    3  2  2  2
     *   s    4  3  3  2
     *   e    5  4  4  3  -> 答案 dp[5][3] = 3
     *
     * 时间复杂度：O(m * n)
     * 空间复杂度：O(m * n)
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();

            // dp[i][j]: word1 前 i 个字符 -> word2 前 j 个字符的最少操作数
            int[][] dp = new int[m+1][n+1];

            // 边界：word2 为空，删除 word1 所有字符
            for (int i = 0; i <= m; i++) {
                dp[i][0] = i;
            }
            // 边界：word1 为空，插入 word2 所有字符
            for (int j = 0; j <= n; j++) {
                dp[0][j] = j;
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        // 字符相同，不需要操作，继承左上角
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        // 字符不同，三种操作取最小值 +1
                        // dp[i-1][j]   : 删除 word1[i-1]
                        // dp[i][j-1]   : 插入 word2[j-1]
                        // dp[i-1][j-1] : 替换 word1[i-1] -> word2[j-1]
                        dp[i][j] = min(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])+1;
                    }
                }
            }
            return dp[m][n];
        }

        private int min(int a, int b, int c) {
            return a < b ? (a < c ? a : b < c ? b : c) : (b < c ? b : a < c ? a : c);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}