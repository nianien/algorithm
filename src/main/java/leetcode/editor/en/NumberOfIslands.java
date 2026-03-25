//200.number-of-islands
/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0
 * 's (water), return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands
 * horizontally or vertically. You may assume all four edges of the grid are all
 * surrounded by water.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * Output: 1
 * <p>
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: grid = [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * Output: 3
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 * <p>
 * <p>
 * Related Topics Array Depth-First Search Breadth-First Search Union-Find Matrix
 * 👍 24884 👎 612
 */

package leetcode.editor.en;

public class NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new NumberOfIslands().new Solution();
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(solution.numIslands(grid));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numIslands(char[][] grid) {
            int ans = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] != '0') {
                        ans++;
                        floodMark(grid, i, j);
                    }
                }
            }
            return ans;
        }

        private void floodMark(char[][] grid, int i, int j) {
            // Use dynamic bounds to support jagged arrays（锯齿数组）
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == '0') {
                return;
            }
            grid[i][j] = '0';
            floodMark(grid, i + 1, j);
            floodMark(grid, i - 1, j);
            floodMark(grid, i, j + 1);
            floodMark(grid, i, j - 1);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}