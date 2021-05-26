package defined

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

func NewListNode(val int, next *ListNode) *ListNode {
	return &ListNode{
		Val:  val,
		Next: next,
	}
}

func (node ListNode) String() string {
	return fmt.Sprintf("%d", node.Val)
}
