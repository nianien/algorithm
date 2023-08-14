package defined

import (
	"container/list"
	"fmt"
)

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func NewTreeNode(val int, left *TreeNode, right *TreeNode) *TreeNode {
	return &TreeNode{
		Val:   val,
		Left:  left,
		Right: right,
	}
}

func (tn TreeNode) String() string {
	if tn.Left == nil && tn.Right == nil {
		return fmt.Sprintf("%d", tn.Val)
	}
	return fmt.Sprintf("[%d,%s,%s]", tn.Val, tn.Left, tn.Right)
}

// BuildTree 按照层次遍历顺序构建树, 中间节点为空null,使用-1表示
func BuildTree(vals ...int) *TreeNode {
	t := &TreeNode{Val: vals[0]}
	var ls = list.List{}
	ls.PushBack(t)
	n := len(vals)
	i := 1
	for i < n {
		h := ls.Front()
		r := h.Value.(*TreeNode)
		ls.Remove(h)
		if vals[i] == -1 {
			r.Left = nil
		} else {
			r.Left = &TreeNode{Val: vals[i]}
			ls.PushBack(r.Left)
		}
		i++
		if i < n {
			if vals[i] == -1 {
				r.Right = nil
			} else {
				r.Right = &TreeNode{Val: vals[i]}
				ls.PushBack(r.Right)
			}
			i++
		}
	}
	return t
}
