//112.path-sum
//给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和
// targetSum 。
//
// 叶子节点 是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//输出：true
//
//
// 示例 2：
//
//
//输入：root = [1,2,3], targetSum = 5
//输出：false
//
//
// 示例 3：
//
//
//输入：root = [1,2], targetSum = 0
//输出：false
//
//
//
//
// 提示：
//
//
// 树中节点的数目在范围 [0, 5000] 内
// -1000 <= Node.val <= 1000
// -1000 <= targetSum <= 1000
//
// Related Topics 树 深度优先搜索
// 👍 581 👎 0

package main

import "fmt"
import (
	. "leetcode/editor/cn/defined"
)

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func hasPathSum(root *TreeNode, targetSum int) bool {
	if root == nil {
		return false
	}
	if root.Left == nil && root.Right == nil && targetSum == root.Val {
		return true
	}
	var sum = targetSum - root.Val
	return root.Left != nil && hasPathSum(root.Left, sum) ||
		root.Right != nil && hasPathSum(root.Right, sum)

}

//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {
	var root = NewTreeNode(5, nil, nil)
	root.Left = NewTreeNode(4, nil, nil)
	root.Left.Left = NewTreeNode(11, nil, nil)
	root.Left.Left.Left = NewTreeNode(7, nil, nil)
	root.Left.Left.Right = NewTreeNode(2, nil, nil)
	root.Right = NewTreeNode(8, nil, nil)
	root.Right.Left = NewTreeNode(13, nil, nil)
	root.Right.Right = NewTreeNode(4, nil, nil)
	root.Right.Right.Right = NewTreeNode(1, nil, nil)
	fmt.Println(hasPathSum(root, 22))
	fmt.Println(hasPathSum(nil, 0))

}
