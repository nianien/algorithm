# 64.minimum-path-sum
# 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
# 
#  说明：每次只能向下或者向右移动一步。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
# 输出：7
# 解释：因为路径 1→3→1→1→1 的总和最小。
#  
# 
#  示例 2： 
# 
#  
# 输入：grid = [[1,2,3],[4,5,6]]
# 输出：12
#  
# 
#  
# 
#  提示： 
# 
#  
#  m == grid.length 
#  n == grid[i].length 
#  1 <= m, n <= 200 
#  0 <= grid[i][j] <= 100 
#  
#  Related Topics 数组 动态规划 
#  👍 888 👎 0
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        m = len(grid)
        n = len(grid[0])
        f = [0] * n
        for i in range(m):
            for j in range(n):
                # 初始化首行
                if i == 0:
                    if j == 0:
                        f[j] = grid[i][j]
                    else:
                        f[j] = grid[i][j] + f[j - 1]
                else:
                    if j == 0:
                        f[j] = grid[i][j] + f[j]
                    else:
                        f[j] = min(f[j - 1], f[j]) + grid[i][j]
        return f[-1]


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().minPathSum([[1, 3, 1], [1, 5, 1], [4, 2, 1]]))
