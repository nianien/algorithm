//96.unique-binary-search-trees
//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：5
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= n <= 19
//
//
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 2317 👎 0

package main

import (
	"fmt"
)

// leetcode submit region begin(Prohibit modification and deletion)
// 给定序列1⋯n，我们选择i作为根，则以根为i的二叉搜索树的集合是左右子树集合的笛卡尔积。
// 我们定义G(n)表示长度为n的二叉搜索树个数; G(i|n)表示以i为根、长度为n的二叉搜索树个数，
// 则有G(i|n)= G(i−1)⋅G(n−i),G(n)= ∑G(i−1)⋅G(n−i)
func numTrees(n int) int {
	//包含空树
	dp := make([]int, n+1)
	dp[0] = 1
	dp[1] = 1
	for i := 2; i <= n; i++ {
		sum := 0
		for j := 1; j <= i; j++ {
			sum += dp[j-1] * dp[i-j]
		}
		dp[i] = sum
	}
	return dp[n]
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	fmt.Println()
}
