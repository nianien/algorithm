//21.merge-two-sorted-lists
//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
//
// 
//
// 示例 1： 
//
// 
//输入：l1 = [1,2,4], l2 = [1,3,4]
//输出：[1,1,2,3,4,4]
// 
//
// 示例 2： 
//
// 
//输入：l1 = [], l2 = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [], l2 = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 两个链表的节点数目范围是 [0, 50] 
// -100 <= Node.val <= 100 
// l1 和 l2 均按 非递减顺序 排列 
// 
// Related Topics 递归 链表 
// 👍 1707 👎 0

package scala.leetcode.editor.cn

import scala.leetcode.editor.cn.TypeDefined.ListNode

object MergeTwoSortedLists extends App {
  //entry from here:
  //leetcode submit region begin(Prohibit modification and deletion)

  /**
   * Definition for singly-linked list.
   * class ListNode(_x: Int = 0, _next: ListNode = null) {
   * var next: ListNode = _next
   * var x: Int = _x
   * }
   */
  object Solution {
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
