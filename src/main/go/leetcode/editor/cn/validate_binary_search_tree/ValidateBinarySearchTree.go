//98.validate-binary-search-tree
//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
//
// 有效 二叉搜索树定义如下：
//
//
// 节点的左子树只包含 小于 当前节点的数。
// 节点的右子树只包含 大于 当前节点的数。
// 所有左子树和右子树自身必须也是二叉搜索树。
//
//
//
//
// 示例 1：
//
//
//输入：root = [2,1,3]
//输出：true
//
//
// 示例 2：
//
//
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
//
//
//
//
// 提示：
//
//
// 树中节点数目范围在[1, 10⁴] 内
// -2³¹ <= Node.val <= 2³¹ - 1
//
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 2100 👎 0

package main

import (
	"fmt"
	"math"
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

func isValidBST(root *TreeNode) bool {
	//中序遍历法

	return isValidBST1(root, new(math.MinInt64))
	//判断判断子树中所有节点的值是否都在 (l,r)开区间范围内
	//return isValidBST0(root, math.MinInt64, math.MaxInt64)
}

// 递归遍历,性能低
func isValidBST0(root *TreeNode, lower, upper int) bool {
	if root == nil {
		return true
	}
	if root.Val <= lower || root.Val >= upper {
		return false
	}
	return isValidBST0(root.Left, lower, root.Val) && isValidBST0(root.Right, root.Val, upper)
}

// 二叉搜索树中序遍历保持递增有序，因此，如果遍历过程中发现逆序，则直接中断返回false
// pre: 上一个访问的节点值
func isValidBST1(root *TreeNode, pre *int) bool {
	if root == nil {
		return true
	}
	if !isValidBST1(root.Left, pre) {
		return false
	}
	if root.Val <= *pre {
		return false
	}
	*pre = root.Val
	if !isValidBST1(root.Right, pre) {
		return false
	}
	return true

}

// 验证左子树的最大节点小于根节点,右子树最小节点大于根节点
func isValidBST2(root *TreeNode) (bool, *TreeNode, *TreeNode) {
	if root.Left == nil && root.Right == nil {
		return true, root, root
	}
	fl, fr := root.Left == nil, root.Right == nil
	var l1, l2, r1, r2 *TreeNode
	if !fl {
		fl, l1, l2 = isValidBST2(root.Left)
		if !fl {
			return false, nil, nil
		}
		fl = l2.Val < root.Val
	} else {
		l1 = root
	}
	if !fr {
		fr, r1, r2 = isValidBST2(root.Right)
		if !fr {
			return false, nil, nil
		}
		fr = r1.Val > root.Val
	} else {
		r2 = root
	}
	return fl && fr, l1, r2
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	t := BuildTree(3, -1, 30, 10, -1, -1, 15, -1, 45)
	fmt.Println(t)
	fmt.Println(isValidBST(t))
	t2 := BuildTree(5, 1, 4, -1, -1, 3, 6)
	//t2 := BuildTree(2, 2, 2)
	fmt.Println(t2)
	fmt.Println(isValidBST(t2))
	t3 := BuildTree(120, 70, 140, 50, 100, 130, 160, 20, 55, 75, 110, 119, 135, 150, 200)
	fmt.Println(t3)
	fmt.Println(isValidBST(t3))
}
