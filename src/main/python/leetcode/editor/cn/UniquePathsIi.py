# 63.unique-paths-ii
# 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。 
# 
#  机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。 
# 
#  现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？ 
# 
#  
# 
#  网格中的障碍物和空位置分别用 1 和 0 来表示。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
# 输出：2
# 解释：
# 3x3 网格的正中间有一个障碍物。
# 从左上角到右下角一共有 2 条不同的路径：
# 1. 向右 -> 向右 -> 向下 -> 向下
# 2. 向下 -> 向下 -> 向右 -> 向右
#  
# 
#  示例 2： 
# 
#  
# 输入：obstacleGrid = [[0,1],[0,0]]
# 输出：1
#  
# 
#  
# 
#  提示： 
# 
#  
#  m == obstacleGrid.length 
#  n == obstacleGrid[i].length 
#  1 <= m, n <= 100 
#  obstacleGrid[i][j] 为 0 或 1 
#  
#  Related Topics 数组 动态规划 
#  👍 557 👎 0

from typing import List


# leetcode submit region begin(Prohibit modification and deletion)


class Solution:
    def uniquePathsWithObstacles2(self, obstacleGrid: List[List[int]]) -> int:
        m = len(obstacleGrid)
        n = len(obstacleGrid[0])
        dp = [[0 for _ in range(n)] for _ in range(m)]
        dp[0][0] = 1
        for i in range(m):
            for j in range(n):
                if obstacleGrid[i][j]:
                    dp[i][j] = 0
                    continue
                # 初始化首行
                if i == 0:
                    if j > 0:
                        # 首行取决于左侧元素
                        dp[i][j] = 1 if dp[i][j - 1] else 0
                else:
                    if j == 0:
                        # 首列取决于上方元素
                        dp[i][j] = 1 if dp[i - 1][j] else 0
                    else:
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        return dp[-1][-1]

    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        m = len(obstacleGrid)
        n = len(obstacleGrid[0])
        # 对于dp[j], 未计算之前,表示grid[i-1][j]的走法,计算之后表示grid[i][j]的走法
        dp = [0] * n
        dp[0] = 1
        for i in range(m):
            for j in range(n):
                # 这里会重置dp[0]
                if obstacleGrid[i][j]:
                    dp[j] = 0
                    continue
                if j > 0:
                    # 右边的dp[j]表示上方位置的走法
                    # dp[j - 1]表示左方位置的走法
                    dp[j] = dp[j] + dp[j - 1]
                # else:
                #     dp[j] = dp[j]
        return dp[-1]


# leetcode submit region end(Prohibit modification and deletion)


# test from here

if __name__ == '__main__':
    print(Solution().uniquePathsWithObstacles([[0, 0, 0], [0, 1, 0], [0, 0, 0]]))
    print(Solution().uniquePathsWithObstacles([[0, 1, 0, 0, 0], [1, 0, 0, 0, 0], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0]]))
