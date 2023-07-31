//143.reorder-list
//给定一个单链表 L 的头节点 head ，单链表 L 表示为：
//
//
//L0 → L1 → … → Ln - 1 → Ln
//
//
// 请将其重新排列后变为：
//
//
//L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
//
// 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
//
//
// 示例 1：
//
//
//
//
//输入：head = [1,2,3,4]
//输出：[1,4,2,3]
//
// 示例 2：
//
//
//
//
//输入：head = [1,2,3,4,5]
//输出：[1,5,2,4,3]
//
//
//
// 提示：
//
//
// 链表的长度范围为 [1, 5 * 10⁴]
// 1 <= node.val <= 1000
//
//
// Related Topics 栈 递归 链表 双指针 👍 1266 👎 0

package main

import (
	"fmt"
)
import . "leetcode/editor/cn/defined"

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */

func reorderList(head *ListNode) {
	n := 0
	for p := head; p != nil; p = p.Next {
		n++
	}
	l := make([]*ListNode, n)
	n = 0
	for p := head; p != nil; p = p.Next {
		l[n] = p
		n++
	}
	i, j := 0, n-1
	for i < j {
		l[i].Next = l[j]
		l[j].Next = l[i+1]
		i++
		j--
	}
	l[i].Next = nil
}

func reorderList2(head *ListNode) {
	mid := middleNode(head)
	h1, h2 := head, mid.Next
	mid.Next = nil
	h2 = reverseList(h2)

	for p, q := h1, h2; p != nil && q != nil; {
		o, e := p, q
		p = p.Next
		q = q.Next
		o.Next = e
		e.Next = p
	}

}

func middleNode(head *ListNode) *ListNode {
	slow, fast := head, head
	for fast.Next != nil && fast.Next.Next != nil {
		slow = slow.Next
		fast = fast.Next.Next
	}
	return slow
}
func reverseList(head *ListNode) *ListNode {
	var prev *ListNode
	cur := head
	for cur != nil {
		next := cur.Next
		cur.Next = prev
		prev = cur
		cur = next
	}
	return prev
}

//leetcode submit region end(Prohibit modification and deletion)

// test from here
func main() {
	h := BuildList(1, 2, 3, 4, 5)
	reorderList2(h)
	fmt.Println(h)
}
