# 120.triangle
# 给定一个三角形 triangle ，找出自顶向下的最小路径和。 
# 
#  每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果
# 正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。 
# 
#  
# 
#  示例 1： 
# 
#  
# 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
# 输出：11
# 解释：如下面简图所示：
#    2
#   3 4
#  6 5 7
# 4 1 8 3
# 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
#  
# 
#  示例 2： 
# 
#  
# 输入：triangle = [[-10]]
# 输出：-10
#  
# 
#  
# 
#  提示： 
# 
#  
#  1 <= triangle.length <= 200 
#  triangle[0].length == 1 
#  triangle[i].length == triangle[i - 1].length + 1 
#  -104 <= triangle[i][j] <= 104 
#  
# 
#  
# 
#  进阶： 
# 
#  
#  你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？ 
#  
#  Related Topics 数组 动态规划 
#  👍 766 👎 0
from typing import List


# leetcode submit region begin(Prohibit modification and deletion)
class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        n = len(triangle)
        dp = [0] * n
        # 计算每行每个位置的最小路径,然后取该行最小值就是当前行的结果
        dp[0] = triangle[0][0]
        for i in range(1, n):
            # 因为计算j+1列时需要上一行的j列结果, 因此这里必须逆序计算,避免j列被覆盖
            for j in range(i, -1, -1):
                # print("[", i, ",", j, "]", triangle[i][j])
                if j == 0:
                    dp[j] = dp[j] + triangle[i][j]
                elif j == i:
                    dp[j] = dp[j - 1] + triangle[i][j]
                else:
                    dp[j] = min(dp[j], dp[j - 1]) + triangle[i][j]
            print(dp)
        return min(dp)


# leetcode submit region end(Prohibit modification and deletion)

# test from here
if __name__ == '__main__':
    print(Solution().minimumTotal([[2], [3, 4], [6, 5, 7], [4, 1, 8, 3]]))
