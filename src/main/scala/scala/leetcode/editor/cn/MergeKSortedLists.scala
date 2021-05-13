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
// 👍 1300 👎 0


package scala.leetcode.editor.cn


import scala.leetcode.editor.cn.TypeDefined.ListNode

object MergeKSortedLists extends App {

  // entry test from here
  val l1: ListNode = ListNode.build(1, 3, 5, 6, 8)
  val l2: ListNode = ListNode.build(2, 4, 7, 9, 10)
  val l3: ListNode = ListNode.build(3, 5, 8, 11, 19, 20)
  System.out.println(Solution.mergeKLists(Array[ListNode](l1, l2, l3)))

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
      merge(lists, 0, lists.length - 1)
    }

    /**
     * 合并start->end的链表
     *
     * @param lists
     * @param start
     * @param end
     * @return
     */
    private def merge(lists: Array[ListNode], start: Int, end: Int): ListNode = {
      if (start == end) return lists(start)
      if (start < end) {
        val mid = (end + start) / 2
        val l1 = merge(lists, start, mid) //合并start->mid的链表
        val l2 = merge(lists, mid + 1, end) //合并mid+1到end的链表
        return merge(l1, l2)
      }
      null
    }

    /**
     * 合并L1，L2两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    private def merge(l1: ListNode, l2: ListNode): ListNode = {
      if (l1 == null) return l2
      if (l2 == null) return l1
      if (l1.x < l2.x) {
        l1.next = merge(l1.next, l2)
        l1
      } else {
        l2.next = merge(l1, l2.next)
        l2
      }
    }
  }

  //leetcode submit region end(Prohibit modification and deletion)

}