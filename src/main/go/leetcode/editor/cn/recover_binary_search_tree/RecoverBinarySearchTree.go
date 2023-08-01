//99.recover-binary-search-tree
//给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
//
//
//
// 示例 1：
//
//
//输入：root = [1,3,null,null,2]
//输出：[3,1,null,null,2]
//解释：3 不能是 1 的左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
//
//
// 示例 2：
//
//
//输入：root = [3,1,4,null,null,2]
//输出：[2,1,4,null,null,3]
//解释：2 不能在 3 的右子树中，因为 2 < 3 。交换 2 和 3 使二叉搜索树有效。
//
//
//
// 提示：
//
//
// 树上节点的数目在范围 [2, 1000] 内
// -2³¹ <= Node.val <= 2³¹ - 1
//
//
//
//
// 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用 O(1) 空间的解决方案吗？
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 870 👎 0

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

func recoverTree(root *TreeNode) {
	morrisInOrder(root)
}

func recoverTree0(root *TreeNode) {
	var pre, recv1, recv2 *TreeNode
	visitInOrder(root, &pre, &recv1, &recv2)
	recv1.Val, recv2.Val = recv2.Val, recv1.Val
}

// 按照中序遍历,访问节点呈递增趋势,如果两个节点调换,则必然产生两次逆序。
// 这里分两种情况:
// 1)相邻节点调换,则节点序列会呈现先升↑后降↓再升，过程中产生1个极大值和1个极小值。
// 2)间隔节点调换,则节点序列会呈现先升↑后降↓再升↑再降↓再升↑，过程中产生2个极大值和2个极小值。
// 以上两种情况,我们只需将第一个极大值和最后一个极小值调换即可恢复。
// pre记录上一次访问节点, recv记录第一个错误节点
func visitInOrder(root *TreeNode, pre **TreeNode, recv1 **TreeNode, recv2 **TreeNode) {
	if root == nil {
		return
	}
	visitInOrder(root.Left, pre, recv1, recv2)
	if *pre != nil && root.Val <= (*pre).Val {
		*recv2 = root      //记录最后一次极小值
		if *recv1 == nil { //第一次逆序,记录极大值和极小值
			*recv1 = *pre
		} else {
			return
		}
	}
	*pre = root
	visitInOrder(root.Right, pre, recv1, recv2)
}

func morrisInOrder(root *TreeNode) {

	var maxV /*极大值*/, minV /*极小值*/, pre /*上一个访问节点*/, predecessor *TreeNode
	for root != nil {
		if root.Left != nil {

			predecessor = root.Left
			//寻找左子树的最右节点
			for predecessor.Right != nil && predecessor.Right != root {
				predecessor = predecessor.Right
			}
			if predecessor.Right == nil {
				// 让最右节点的右子树指向root，继续循环
				predecessor.Right = root
				root = root.Left
			} else {
				predecessor.Right = nil
				// 此时,第二次访问到根节点, 说明左子树已经访问完了，我们需要断开链接
				if pre != nil && root.Val <= pre.Val {
					minV = root
					if maxV == nil {
						maxV = pre
					} else {
						break
					}
				}
				pre = root

				//访问右子树
				root = root.Right
			}
		} else { // 如果没有左孩子，则直接访问右孩子
			if pre != nil && root.Val < pre.Val {
				minV = root
				if maxV == nil {
					maxV = pre
				}
			}
			pre = root
			root = root.Right
		}
	}
	fmt.Println("++++")
	maxV.Val, minV.Val = minV.Val, maxV.Val
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	t := BuildTree(1, 3, -1, -1, 2)
	fmt.Println(t)
	recoverTree(t)
	fmt.Println(t)
	t2 := BuildTree(3, 1, 4, -1, -1, 2)
	fmt.Println(t2)
	recoverTree(t2)
	fmt.Println(t2)
}
