package com.lining;

import leetcode.editor.cn.TypeDefined.ListNode;

public class ListQuickSort {


    public static void main(String[] args) {
        ListNode head = ListNode.build(2, 1, 3, 5);

        System.out.println(sort(head));
    }


    private static ListNode merge(ListNode p1, ListNode p2) {
        if (p1 == null) {
            return p2;
        }
        if (p2 == null) {
            return p1;
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                tail.next = p1;
                p1 = p1.next;
            } else {
                tail.next = p2;
                p2 = p2.next;
            }
            tail = tail.next;
        }
        if (p1 == null) {
            tail.next = p2;
        } else {
            tail.next = p1;
        }
        return head.next;
    }

    private static ListNode sort(ListNode list) {
        if (list == null || list.next == null) {
            return list;
        }
        ListNode slow = list;
        ListNode fast = list.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode left = list;
        ListNode right = slow.next;
        slow.next = null;
        return merge(sort(left), sort(right));

    }


}