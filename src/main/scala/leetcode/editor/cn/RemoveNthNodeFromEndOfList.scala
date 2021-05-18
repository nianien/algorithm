package leetcode.editor.cn

import leetcode.editor.scala.ListNode


object RemoveNthNodeFromEndOfList extends App {
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
