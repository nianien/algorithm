# 62.unique-paths
# 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。 
# 
#  机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。 
# 
#  问总共有多少条不同的路径？ 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：m = 3, n = 7
# 输出：28 
# 
#  示例 2： 
# 
#  
# 输入：m = 3, n = 2
# 输出：3
# 解释：
# 从左上角开始，总共有 3 条路径可以到达右下角。
# 1. 向右 -> 向下 -> 向下
# 2. 向下 -> 向下 -> 向右
# 3. 向下 -> 向右 -> 向下
#  
# 
#  示例 3： 
# 
#  
# 输入：m = 7, n = 3
# 输出：28
#  
# 
#  示例 4： 
# 
#  
# 输入：m = 3, n = 3
# 输出：6 
# 
#  
# 
#  提示： 
# 
#  
#  1 <= m, n <= 100 
#  题目数据保证答案小于等于 2 * 109 
#  
#  Related Topics 数组 动态规划 
#  👍 1004 👎 0

# leetcode submit region begin(Prohibit modification and deletion)
class Solution:

    def uniquePaths(self, m: int, n: int) -> int:
        # print(m, ",", n)
        # 初始化dp数组,然后依次计算f(m,n),复用上次计算结果
        dp = [[0 for _ in range(n)] for _ in range(m)]
        for i in range(0, m):
            for j in range(0, n):
                # 初始化首行和首列
                if i == 0 or j == 0:
                    dp[i][j] = 1
                else:
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
        return dp[-1][-1]

    def uniquePaths2(self, m: int, n: int) -> int:
        # 空间优化
        # d[j]未计算时表示上一行结果, 计算后表示当前行结果
        dp = [1] * n
        for i in range(0, m):
            for j in range(0, n):
                # 赋值表达式左边:
                # dp[j]表示上方位置,即上一行该列结果
                # dp[j - 1]表示左方位置,即当前行前一列结果
                dp[j] = dp[j] + dp[j - 1]
        return dp[-1][-1]


# leetcode submit region end(Prohibit modification and deletion)


# test from here
if __name__ == '__main__':
    print(Solution().uniquePaths(7, 3))
    # m, n = (4, 5)
    # dp = [[0 for _ in range(n)] for _ in range(m)]
    # for i in range(m):
    #     dp[i][0] = 1
    # for i in range(n):
    #     dp[0][i] = 1
    # for r in dp:
    #     print(r)
