package defined

import "strconv"

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

func (this *ListNode) String() string {
	p := this
	s := "|"
	id := 1
	var nodes = make(map[*ListNode]int)
	for p != nil {
		s += "->" + strconv.Itoa(p.Val)
		id_, ok := nodes[p]
		if ok {
			s += "*(" + strconv.Itoa(id_) + ")"
			break
		}
		nodes[p] = id
		id += 1
		p = p.Next
	}
	return s
}

func BuildList(vals ...int) *ListNode {
	h := new(ListNode)
	p := h
	for _, val := range vals {
		p.Next = &ListNode{
			Val: val,
		}
		p = p.Next
	}
	return h.Next
}
