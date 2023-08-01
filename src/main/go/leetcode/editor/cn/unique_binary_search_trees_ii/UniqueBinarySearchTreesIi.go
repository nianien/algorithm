//95.unique-binary-search-trees-ii
//给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
//
//
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
//
//
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
//
// Related Topics 树 二叉搜索树 动态规划 回溯 二叉树 👍 1458 👎 0

package main

import (
	"fmt"
)
import . "leetcode/editor/cn/defined"

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func generateTrees(n int) []*TreeNode {
	nums := make([]int, n)
	for i := 0; i < n; i++ {
		nums[i] = i + 1
	}
	return generateTrees0(nums)
}

// 首先把n转成1..n的数组，然后依次尝试使用数组中的每一个元素作为根节点，从而将数组进行分割成两部分，依次递归分割后的数组，左边半部分的作为左子树，右半部分作为右子树，左右子树的全部组合就是最终结果。
func generateTrees0(nums []int) []*TreeNode {
	n := len(nums)
	if n == 0 {
		return []*TreeNode{nil}
	}
	if n == 1 {
		return []*TreeNode{{Val: nums[0]}}
	}
	var res []*TreeNode
	for i := 0; i < n; i++ {
		left := generateTrees0(nums[:i])
		right := generateTrees0(nums[i+1:])
		for _, l := range left {
			for _, r := range right {
				res = append(res, &TreeNode{Val: nums[i], Left: l, Right: r})
			}
		}
	}
	return res
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	trees := generateTrees(3)
	for _, tree := range trees {
		fmt.Println(tree)
	}
}
