package leetcode.editor.java;

import javax.xml.soap.Node;
import java.util.HashSet;
import java.util.Set;

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
}