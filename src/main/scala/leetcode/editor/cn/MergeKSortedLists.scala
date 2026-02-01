//23.merge-k-sorted-lists
//给你一个链表数组，每个链表都已经按升序排列。 
//
// 请你将所有链表合并到一个升序链表中，返回合并后的链表。 
//
// 
//
// 示例 1： 
//
// 输入：lists = [[1,4,5],[1,3,4],[2,6]]
//输出：[1,1,2,3,4,4,5,6]
//解释：链表数组如下：
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//将它们合并到一个有序链表中得到。
//1->1->2->3->4->4->5->6
// 
//
// 示例 2： 
//
// 输入：lists = []
//输出：[]
// 
//
// 示例 3： 
//
// 输入：lists = [[]]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// k == lists.length 
// 0 <= k <= 10^4 
// 0 <= lists[i].length <= 500 
// -10^4 <= lists[i][j] <= 10^4 
// lists[i] 按 升序 排列 
// lists[i].length 的总和不超过 10^4 
// 
// Related Topics 堆 链表 分治算法 
// 👍 1306 👎 0

package leetcode.editor.cn

import leetcode.editor.cn.defined.scala.ListNode


object MergeKSortedLists extends App {

  //leetcode submit region begin(Prohibit modification and deletion)

  /**
   * Definition for singly-linked list.
   * class ListNode(_x: Int = 0, _next: ListNode = null) {
   * var next: ListNode = _next
   * var x: Int = _x
   * }
   */
  object Solution {
    def mergeKLists(lists: Array[ListNode]): ListNode = {
      mergeKLists(lists, 0, lists.length - 1)
    }

    private def mergeKLists(lists: Array[ListNode], begin: Int, end: Int): ListNode = {
      if (begin == end) {
        return lists(begin)
      }
      val mid = (begin + end) / 2
      val left = mergeKLists(lists, begin, mid)
      val right = mergeKLists(lists, mid + 1, end)
      mergeLists(left, right)
    }

//    private def mergeLists(l1: ListNode, l2: ListNode): ListNode = {
//      if (l1 == null) return l2
//      if (l2 == null) return l1
//      if (l1.x < l2.x) {
//        l1.next = mergeLists(l1.next, l2)
//        l1
//      } else {
//        l2.next = mergeLists(l1, l2.next)
//        l2
//      }
//    }

    private def mergeLists(l1: ListNode, l2: ListNode): ListNode = {
      // dummy 头结点，避免特判
      val dummy = new ListNode(0)
      var tail = dummy

      var p1 = l1
      var p2 = l2

      while (p1 != null && p2 != null) {
        if (p1.x <= p2.x) {
          tail.next = p1
          p1 = p1.next
        } else {
          tail.next = p2
          p2 = p2.next
        }
        tail = tail.next
      }

      // 剩余部分直接接上
      if (p1 != null) tail.next = p1
      if (p2 != null) tail.next = p2

      dummy.next
    }
  }

  //leetcode submit region end(Prohibit modification and deletion)
  val l1 = ListNode.build(1, 3, 5, 6, 8);
  val l2 = ListNode.build(2, 4, 7, 9, 10);
  val l3 = ListNode.build(3, 5, 8, 11, 19, 20);
  println(Solution.mergeKLists(Array(l1, l2, l3)))
}
