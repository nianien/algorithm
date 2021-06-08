package defined

import "fmt"

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

func (node TreeNode) String() string {
	return fmt.Sprintf("[val:%d]", node.Val)
}
