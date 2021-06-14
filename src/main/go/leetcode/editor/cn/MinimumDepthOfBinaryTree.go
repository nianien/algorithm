//111.minimum-depth-of-binary-tree
//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
// 说明：叶子节点是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：2
//
//
// 示例 2：
//
//
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
//
//
//
//
// 提示：
//
//
// 树中节点数的范围在 [0, 105] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 广度优先搜索
// 👍 524 👎 0

package main

import "fmt"

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
func minDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	if root.Left == nil && root.Right == nil {
		return 1
	}
	if root.Left == nil && root.Right != nil {
		return minDepth(root.Right) + 1
	}
	if root.Left != nil && root.Right == nil {
		return minDepth(root.Left) + 1
	}
	var l = minDepth(root.Left) + 1
	var r = minDepth(root.Right) + 1
	if l < r {
		return l
	} else {
		return r
	}
}

//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {
	var root = NewTreeNode(3,
		NewTreeNode(9, nil, nil),
		NewTreeNode(20, NewTreeNode(15, nil, nil), NewTreeNode(7, nil, nil)))
	fmt.Println(minDepth(root))
}
