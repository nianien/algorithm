//23.给你一个链表数组，每个链表都已经按升序排列。
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
// 👍 1210 👎 0


package leetcode.editor.cn;

import leetcode.editor.cn.TypeDefined.ListNode;

public class MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new MergeKSortedLists().new Solution();
        ListNode l1 = ListNode.build(1, 3, 5, 6, 8);
        ListNode l2 = ListNode.build(2, 4, 7, 9, 10);
        ListNode l3 = ListNode.build(3, 5, 8, 11, 19, 20);
        System.out.println(solution.mergeKLists(new ListNode[]{l1, l2, l3}));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
//            ListNode head = new ListNode();
//            ListNode tail = head;
//            for (; ; ) {
//                ListNode min = null;
//                int index = -1;
//                for (int i = 0; i < lists.length; i++) {
//                    if (lists[i] == null) {
//                        continue;
//                    }
//                    if (min == null) {
//                        min = lists[i];
//                        index = i;
//                    } else if (min.val > lists[i].val) {
//                        min = lists[i];
//                        index = i;
//                    }
//                }
//                if (index == -1) {
//                    break;
//                }
//                tail.next = min;
//                lists[index] = min.next;
//                tail = tail.next;
//            }
//            return head.next;
            return merge(lists, 0, lists.length - 1);
        }


        /**
         * 合并start->end的链表
         *
         * @param lists
         * @param start
         * @param end
         * @return
         */
        private ListNode merge(ListNode[] lists, int start, int end) {
            if (start == end) {
                return lists[start];
            }
            if (start < end) {
                int mid = (end + start) / 2;
                ListNode l1 = merge(lists, start, mid);  //合并start->mid的链表
                ListNode l2 = merge(lists, mid + 1, end);  //合并mid+1到end的链表
                return merge(l1, l2);
            }

            return null;
        }

        /**
         * 合并L1，L2两个有序链表
         *
         * @param l1
         * @param l2
         * @return
         */
        private ListNode merge(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;

            if (l1.val < l2.val) {
                l1.next = merge(l1.next, l2);
                return l1;
            } else {
                l2.next = merge(l1, l2.next);
                return l2;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}