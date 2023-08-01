//655.print-binary-tree
//给你一棵二叉树的根节点 root ，请你构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res ，用以表示树的 格式化布局 。构造此格式化布局矩
//阵需要遵循以下规则：
//
//
// 树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。
// 矩阵的列数 n 应该等于 2ʰᵉⁱᵍʰᵗ⁺¹ - 1 。
// 根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
// 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2ʰᵉⁱᵍʰᵗ⁻ʳ⁻¹] ，右子节点放置在
//res[r+1][c+2ʰᵉⁱᵍʰᵗ⁻ʳ⁻¹] 。
// 继续这一过程，直到树中的所有节点都妥善放置。
// 任意空单元格都应该包含空字符串 "" 。
//
//
// 返回构造得到的矩阵 res 。
//
//
//
//
//
// 示例 1：
//
//
//输入：root = [1,2]
//输出：
//[["","1",""],
// ["2","",""]]
//
//
// 示例 2：
//
//
//输入：root = [1,2,3,null,4]
//输出：
//[["","","","1","","",""],
// ["","2","","","","3",""],
// ["","","4","","","",""]]
//
//
//
//
// 提示：
//
//
// 树中节点数在范围 [1, 2¹⁰] 内
// -99 <= Node.val <= 99
// 树的深度在范围 [1, 10] 内
//
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 214 👎 0

package main

import (
	"container/list"
	"fmt"
	"strconv"
)
import . "leetcode/editor/cn/defined"

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree n.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func printTree(root *TreeNode) [][]string {
	h := height(root)
	m := h + 1
	n := 1<<m - 1
	arr := make([][]string, m)
	for i := range arr {
		arr[i] = make([]string, n)
	}
	//根据当前节点设置数组元素,然后递归设置左右子节点
	var dfs func(*TreeNode, int, int)
	dfs = func(node *TreeNode, i, j int) {
		arr[i][j] = strconv.Itoa(node.Val)
		if node.Left != nil {
			dfs(node.Left, i+1, j-1<<(h-i-1))
		}
		if node.Right != nil {
			dfs(node.Right, i+1, j+1<<(h-i-1))
		}
	}
	dfs(root, 0, (n-1)/2)
	return arr
}

// 层次遍历
// 先将根节点放置到初始位置,同时把根节点放置到队列
// 然后从第一行开始遍历二维数组, 当扫描到元素不为空时,即为队列中第一个节点
// 此时当前节点出队列, 然后放置该节点的左右子节点,同时左右子节点入队列
func printTree2(root *TreeNode) [][]string {
	h := height(root)
	m := h + 1
	n := 1<<m - 1
	arr := make([][]string, m)
	for i := range arr {
		arr[i] = make([]string, n)
	}
	arr[0][(n-1)/2] = strconv.Itoa(root.Val)
	ls := list.List{}
	ls.PushBack(root)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if arr[i][j] != "" {
				node := ls.Front().Value.(*TreeNode)
				ls.Remove(ls.Front())
				arr[i][j] = strconv.Itoa(node.Val)
				if node.Left != nil {
					ls.PushBack(node.Left)
					arr[i+1][j-1<<(h-i-1)] = strconv.Itoa(node.Left.Val)
				}
				if node.Right != nil {
					ls.PushBack(node.Right)
					arr[i+1][j+1<<(h-i-1)] = strconv.Itoa(node.Right.Val)
				}
			}
		}
	}
	return arr
}
func height(root *TreeNode) int {
	if root == nil || root.Left == nil && root.Right == nil {
		return 0
	}
	return max(height(root.Left), height(root.Right)) + 1
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	t := BuildTree(1, 2)
	fmt.Println(printTree(t))
	fmt.Println(printTree2(t))
}
