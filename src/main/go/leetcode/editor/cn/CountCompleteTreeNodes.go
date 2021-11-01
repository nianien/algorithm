//222.count-complete-tree-nodes
//给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
//
// 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层
//为第 h 层，则该层包含 1~ 2h 个节点。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3,4,5,6]
//输出：6
//
//
// 示例 2：
//
//
//输入：root = []
//输出：0
//
//
// 示例 3：
//
//
//输入：root = [1]
//输出：1
//
//
//
//
// 提示：
//
//
// 树中节点的数目范围是[0, 5 * 104]
// 0 <= Node.val <= 5 * 104
// 题目数据保证输入的树是 完全二叉树
//
//
//
//
// 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
// Related Topics 树 二分查找
// 👍 500 👎 0

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
func countNodes(root *TreeNode) int {
	if root == nil {
		return 0
	}
	//计算树的高度,根节点为0层
	var level = 0
	var node = root
	for node.Left != nil {
		level++
		node = node.Left
	}
	//根节点记为1，从根节点到任意节点的路径，左子节点记为0，右子节点记为1，生成一个二进制数，该数字为节点先跟遍历的顺序
	//由上，则第一个叶子节点的顺序为k=2^h,任意一个叶子节点的顺序为[2^h,2^(h+1)-1]
	//假设一个叶子节点对应的二进制为110001,则表示根->右->左->左->左->右的路径，通过该路径就可以找到该叶子节点，并判断是否为空
	//如果为空，则说明该叶子节点不存在，二分查找最后一个叶子节点，其对应二进制表示就是节点总数

	//2^h
	var low = 1 << level
	//2^(h+1)-1
	var high = (1 << level << 1) - 1
	for low < high {
		//注：加1中间偏右，不加1中间偏左
		var mid = (high + low + 1) / 2
		if exists(root, level, mid) {
			//这里mid一定存在，所以返回值为low的边界值
			low = mid
		} else {
			high = mid - 1
		}
	}
	return low
}

/**
判断第K个元素是否存在
*/
func exists(root *TreeNode, level int, k int) bool {
	//从根节点到节点K的路径表示，不含根节点
	var bits = 1 << (level - 1)
	//fmt.Println(strconv.FormatInt(int64(bits), 2))
	var node = root
	//bits=0表示查找结束，node！=null表示节点存在
	for node != nil && bits > 0 {
		if (bits & k) == 0 {
			node = node.Left
		} else {
			node = node.Right
		}
		bits >>= 1
	}
	return node != nil
}

//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {
	var root = NewTreeNode(1,
		NewTreeNode(2, NewTreeNode(4, nil, nil), NewTreeNode(5, nil, nil)),
		NewTreeNode(3, nil, nil))
	fmt.Println(countNodes(root))
	fmt.Println(1 << 3 << 1)
	fmt.Println(1 << (3 + 1))
}
