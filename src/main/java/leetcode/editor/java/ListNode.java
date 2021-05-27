package leetcode.editor.java;

import java.util.HashMap;
import java.util.Map;

public class ListNode {
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

    public static ListNode build(int... values) {
        ListNode h = new ListNode(-1);
        ListNode p = h;
        for (int value : values) {
            p.next = new ListNode(value);
            p = p.next;
        }
        return h.next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("|");
        ListNode current = this;
        Map<ListNode, Integer> nodes = new HashMap<>();
        int id = 1;
        while (current != null) {
            sb.append("->");
            sb.append(current.val);
            if (nodes.containsKey(current)) {
                sb.append("*(").append(nodes.get(current)).append(")");
                break;
            } else {
                nodes.put(current, id++);
            }
            current = current.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n3;
        System.out.println(n1);
    }
}