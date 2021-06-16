package com.lining;

import leetcode.editor.cn.defined.java.ListNode;

/**
 * scm.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
public class ListReverse {

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = pre.next;
        pre.next = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public static ListNode reverse2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode next = pre.next;
        pre.next = null;
        ListNode tail = reverse2(next);
        next.next=pre;
        return tail;
    }


    public static void main(String[] args) {
        ListNode l = ListNode.build(1, 2, 3);
        System.out.println(l);
        ListNode r = reverse2(l);
        System.out.println(r);
    }

}
