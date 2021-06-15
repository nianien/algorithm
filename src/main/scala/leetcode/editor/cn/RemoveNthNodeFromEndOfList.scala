//19.remove-nth-node-from-end-of-list
//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
// Related Topics 链表 双指针 
// 👍 1409 👎 0

package leetcode.editor.cn

import leetcode.editor.scala.ListNode

object RemoveNthNodeFromEndOfList extends App{
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * class ListNode(_x: Int = 0, _next: ListNode = null) {
     * var next: ListNode = _next
     * var x: Int = _x
     * }
     */
    object Solution {
        def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
            val dummy = new ListNode(-1, head)
            var (begin, end, length) = (dummy, dummy, n)
            //这里,n如果超过链表长度则删除第一个节点
            while (end.next != null && length > 0) {
                end = end.next
                length -= 1
            }
            while (end.next != null) {
                begin = begin.next
                end = end.next
            }
            begin.next = begin.next.next
            dummy.next
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    //test from here:
    println(Solution.removeNthFromEnd(ListNode.build(1), 2))
}