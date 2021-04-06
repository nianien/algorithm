package leetcode.editor.cn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * scm.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
public class TypeDefined {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode current = this;
            boolean isFirst = true;
            while (current != null) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append("->");
                }
                sb.append(current.val);
                current = current.next;
            }
            return sb.toString();
        }

        public static ListNode build(int... values) {
            ListNode h = new ListNode(-1);
            ListNode p = h;
            for (int value : values) {
                p.next = new ListNode(value);
                p = p.next;
            }
            return h.next;
        }
    }


    /**
     * 整型列表
     *
     * @param arr
     * @return
     */
    public static List<Integer> toList(int[] arr) {
        return Arrays.stream(arr).mapToObj(i -> i).collect(Collectors.toList());
    }

    /**
     * 整型数组
     *
     * @param list
     * @return
     */
    public static int[] toInts(List<Integer> list) {
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

}
