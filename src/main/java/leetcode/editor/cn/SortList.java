//148.sort-list
//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 104] 内 
// -105 <= Node.val <= 105 
// 
// Related Topics 排序 链表 
// 👍 1121 👎 0


package leetcode.editor.cn;


import leetcode.editor.defined.java.ListNode;

public class SortList {
    public static void main(String[] args) {
        Solution solution = new SortList().new Solution();
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
        public ListNode sortList(ListNode head) {
            return sortTop2Bottom(head);
        }

        /**
         * 自底向上归并排序
         *
         * @param head
         * @return
         */
        public ListNode sortBottom2Top(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // 统计链表长度
            int length = length(head);
            // 引入dummy-node
            ListNode dummy = new ListNode(0, head);
            // 每次将链表拆分成若干个长度为size的子链表 , 并按照每两个子链表一组进行合并
            for (int size = 1; size < length; size <<= 1) {
                ListNode tail = dummy;
                ListNode cur = dummy.next;
                while (cur != null) {
                    ListNode left = cur;
                    //left为头节点,截取size个节点, right为剩余链表头节点
                    ListNode right = cut(left, size);
                    //right为头节点,窃取size个节点, cur为剩余链表头节点
                    cur = cut(right, size);
                    tail.next = mergeTwoLists(left, right);
                    //合并后的尾节点
                    while (tail.next != null) {
                        tail = tail.next;
                    }
                }
            }
            // 返回新排好序的链表
            return dummy.next;
        }

        /**
         * 截取前n个节点,返回截断后的头节点
         *
         * @param head
         * @param n
         * @return
         */
        private ListNode cut(ListNode head, int n) {
            ListNode p = head;
            while (p != null && --n > 0) {
                p = p.next;
            }
            if (p == null) return null;
            ListNode next = p.next;
            p.next = null;
            return next;
        }


        private int length(ListNode node) {
            int length = 0;
            while (node != null) {
                length++;
                node = node.next;
            }
            return length;
        }

        /**
         * 自上向下归并
         *
         * @param head
         * @return
         */
        private ListNode sortTop2Bottom(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode left = head;
            ListNode right = slow.next;
            //断链
            slow.next = null;
            return mergeTwoLists(sortTop2Bottom(left), sortTop2Bottom(right));

        }


        private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            while (l1 != null && l2 != null) { // 退出循环的条件是走完了其中一个链表
                if (l1.val < l2.val) {
                    cur.next = l1;
                    l1 = l1.next;
                } else {
                    cur.next = l2;
                    l2 = l2.next;
                }
                cur = cur.next;
            }
            if (l1 == null) cur.next = l2;
            if (l2 == null) cur.next = l1;
            // 最后返回合并后有序的链表
            return dummy.next;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}