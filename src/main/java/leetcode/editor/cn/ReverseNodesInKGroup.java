//25.给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
//
// k 是一个正整数，它的值小于或等于链表的长度。 
//
// 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 
//
// 进阶： 
//
// 
// 你可以设计一个只使用常数额外空间的算法来解决此问题吗？ 
// 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,4,5], k = 2
//输出：[2,1,4,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,2,3,4,5], k = 3
//输出：[3,2,1,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2,3,4,5], k = 1
//输出：[1,2,3,4,5]
// 
//
// 示例 4： 
//
// 
//输入：head = [1], k = 1
//输出：[1]
// 
//
// 
// 
//
// 提示： 
//
// 
// 列表中节点的数量在范围 sz 内 
// 1 <= sz <= 5000 
// 0 <= Node.val <= 1000 
// 1 <= k <= sz 
// 
// Related Topics 链表 
// 👍 992 👎 0


package leetcode.editor.cn;


import leetcode.editor.cn.java.ListNode;

public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        Solution solution = new ReverseNodesInKGroup().new Solution();
        ListNode head = ListNode.build(1, 2, 3, 4, 5);
        System.out.println(head);
        System.out.println(solution.reverseKGroup(head, 2));
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
        public ListNode reverseKGroup(ListNode head, int k) {

            if (head == null || head.next == null || k == 1) {
                return head;
            }
            ListNode tail = head;
            for (int i = 0; i < k; i++) {
                //剩余数量小于k的话，则不需要反转。
                if (tail == null) {
                    return head;
                }
                tail = tail.next;
            }
            // 反转前 k 个元素, 第K个节点是新表头
            ListNode newHead = reverse(head, tail);
            //下一轮的开始的地方就是tail
            head.next = reverseKGroup(tail, k);
            return newHead;
        }

        /**
         * 左开右闭, 返回翻转后的表头,即tail前一个节点
         *
         * @param head
         * @param tail
         * @return
         */
        private ListNode reverse(ListNode head, ListNode tail) {
            ListNode pre = null, next;
            while (head != tail) {
                //翻转节点
                next = head.next;
                head.next = pre;
                //后移指针
                pre = head;
                head = next;
            }
            return pre;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

}
