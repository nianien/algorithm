package leetcode.editor.cn

import leetcode.editor.scala.ListNode



object MergeTwoSortedLists extends App {


  //leetcode submit region begin(Prohibit modification and deletion)

  /**
   * Definition for singly-linked list.
   * class ListNode(_x: Int = 0, _next: ListNode = null) {
   * var next: ListNode = _next
   * var x: Int = _x
   * }
   */
  object Solution {
    /**
     *
     * @param l1
     * @param l2
     * @return
     */
    def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
      val dummy = new ListNode(-1, null)
      var (h1, h2, p) = (l1, l2, dummy)
      while (h1 != null && h2 != null) {
        if (h1.x < h2.x) {
          p.next = h1
          h1 = h1.next
        } else {
          p.next = h2
          h2 = h2.next
        }
        p = p.next
      }
      if (h1 != null) {
        p.next = h1
      }
      if (h2 != null) {
        p.next = h2
      }
      dummy.next
    }
  }
  //leetcode submit region end(Prohibit modification and deletion)
}

