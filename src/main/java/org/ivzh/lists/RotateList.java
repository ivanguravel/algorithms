package org.ivzh.lists;

import java.util.List;

public class RotateList {

    public static void main(String[] args) {
        ListNode f = new ListNode(1);

        ListNode s = new ListNode(2);
        f.next = s;

        ListNode t = new ListNode(3);
        s.next = t;

        ListNode test = rotateRight(f, 1);

        while (test != null) {
            System.out.println(test.val);
            test = test.next;
        }

    }


    public static ListNode rotateRight(ListNode head, int k) {
        ListNode result = head;
        ListNode firstNode;
        firstNode = head;
        ListNode lastNode = getLast(head);
        for (int i = 0; i < k; i++) {
            int first = firstNode.val;
            int last = lastNode.val;
            while (head.next != null) {
                int tmp = head.next.val;
                head.next.val = first;
                first = tmp;
                head = head.next;
            }
            firstNode.val = last;
        }
        return result;
    }

    private static ListNode getLast(ListNode head) {
        ListNode last = head;
        while (head != null) {
            if (head.next == null) {
                last = head;
            }
            head = head.next;
        }
        return last;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
    }
}
