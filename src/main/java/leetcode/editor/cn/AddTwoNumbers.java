
//2.add-two-numbers
//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//
//
// 示例 1：
//
//
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
//
//
// 示例 2：
//
//
//输入：l1 = [0], l2 = [0]
//输出：[0]
//
//
// 示例 3：
//
//
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
//
//
//
//
// 提示：
//
//
// 每个链表中的节点数在范围 [1, 100] 内
// 0 <= Node.val <= 9
// 题目数据保证列表表示的数字不含前导零
//
// Related Topics 递归 链表 数学
// 👍 6167 👎 0


package leetcode.editor.cn;

import leetcode.editor.java.ListNode;

public class AddTwoNumbers {
    public static void main(String[] args) {
        Solution solution = new AddTwoNumbers().new Solution();
        System.out.println(solution.addTwoNumbers(ListNode.build(9, 9, 9, 9, 9, 9, 9), ListNode.build(9, 9, 9, 9)));
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
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int n = 0;
            ListNode dummy = new ListNode();
            ListNode head = dummy;
            while (l1 != null || l2 != null) {
                if (l1 != null) {
                    n += l1.val;
                    l1 = l1.next;
                }
                if (l2 != null) {
                    n += l2.val;
                    l2 = l2.next;
                }
                head.next = new ListNode(n % 10);
                head = head.next;
                n /= 10;
            }
            if (n > 0) {
                head.next = new ListNode(n);
            }
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}