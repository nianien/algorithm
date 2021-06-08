//101.symmetric-tree
//给定一个二叉树，检查它是否是镜像对称的。
//
//
//
// 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
//
//     1
//   / \
//  2   2
// / \ / \
//3  4 4  3
//
//
//
//
// 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
//
//     1
//   / \
//  2   2
//   \   \
//   3    3
//
//
//
//
// 进阶：
//
// 你可以运用递归和迭代两种方法解决这个问题吗？
// Related Topics 树 深度优先搜索 广度优先搜索
// 👍 1409 👎 0

package main

import (
	"fmt"
	. "main/go/leetcode/editor/cn/defined"
)

//leetcode submit region begin(Prohibit modification and deletion)

import (
	. "container/list"
)

/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */
func isSymmetric(root *TreeNode) bool {
	return isSymmetricLevel(root)
}

/**
判断当前层及以下层是否对称
*/
func isSymmetricList2(root *TreeNode) bool {
	if root == nil || root.Left == nil && root.Right == nil {
		return true
	}
	q := New()
	q.PushFront(root.Left)
	q.PushFront(root.Right)
	// 这里是迭代判断,即先比较最左子树左节点和右子树右节点
	// 再比较左子树右节点和右子树左节点
	for q.Len() > 0 {
		var u = q.Front().Value.(*TreeNode)
		q.Remove(q.Front())
		var v = q.Front().Value.(*TreeNode)
		q.Remove(q.Front())
		if u == nil && v == nil {
			continue
		} else if u == nil || v == nil {
			return false
		} else if u.Val != v.Val {
			return false
		}
		//这里对称加入队列,即<左.左,右.右>,<左.右,右.左>
		//在后续遍历时可以保持对称访问
		q.PushFront(u.Left)
		q.PushFront(v.Right)
		q.PushFront(u.Right)
		q.PushFront(v.Left)
	}
	return true

}

/**
层次遍历,判断是否为回文数组
*/
func isSymmetricLevel(root *TreeNode) bool {
	l := New()
	l.PushFront(root)
	for l.Len() > 0 {
		if !isSymmetricVal(l) {
			return false
		}
		// 逆序是为了节省链表长度变量
		// 将上一层的节点从前端删除,下一层的节点在尾端添加
		for i := l.Len(); i > 0; i-- {
			var h = l.Front()
			l.Remove(h)
			var node = h.Value.(*TreeNode)
			if node != nil {
				l.PushBack(node.Left)
				l.PushBack(node.Right)
			}
		}
	}
	return true
}

/**
判断是否回文数组
*/
func isSymmetricVal(l *List) bool {
	if l.Len() <= 1 {
		return true
	}
	var f = l.Front()
	var b = l.Back()
	//已经相遇  b.Next()==f ||f==b
	for f != b && b.Next() != f {
		var v1 = f.Value.(*TreeNode)
		var v2 = b.Value.(*TreeNode)
		if v1 == nil && v2 != nil ||
			v1 != nil && v2 == nil ||
			v1 != nil && v2 != nil && v1.Val != v2.Val {
			return false
		}
		f = f.Next()
		b = b.Prev()
	}
	return true
}

//leetcode submit region end(Prohibit modification and deletion)

//test from here
func main() {
	var root = NewTreeNode(1,
		NewTreeNode(2, nil, nil),
		NewTreeNode(2, nil, nil))
	var root2 = NewTreeNode(1,
		NewTreeNode(2, nil, nil),
		NewTreeNode(2, nil, NewTreeNode(2, nil, nil)))
	fmt.Println(isSymmetric(root))
	fmt.Println(isSymmetricList2(root2))
}
