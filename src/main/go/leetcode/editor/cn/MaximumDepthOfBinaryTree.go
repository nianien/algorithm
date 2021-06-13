//104.maximum-depth-of-binary-tree
//给定一个二叉树，找出其最大深度。
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
//
// 说明: 叶子节点是指没有子节点的节点。
//
// 示例：
//给定二叉树 [3,9,20,null,null,15,7]，
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// 返回它的最大深度 3 。
// Related Topics 树 深度优先搜索 递归
// 👍 898 👎 0

package main

import (
	"fmt"
)
import . "main/go/leetcode/editor/cn/defined"

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func maxDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	var l = maxDepth(root.Left) + 1
	var r = maxDepth(root.Right) + 1
	if l > r {
		return l
	} else {
		return r
	}
}

//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {
	fmt.Println()
}
