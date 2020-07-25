package org.ivzh.lists;


import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/rotate-list/
// TL
public class RotateList {

    public static void main(String[] args) {
        ListNode f = new ListNode(1);

        ListNode s = new ListNode(2);
        f.next = s;

        ListNode t = new ListNode(3);
        s.next = t;

        ListNode test = rotateRight(f, 2000000);

        while (test != null) {
            System.out.println(test.val);
            test = test.next;
        }

       // System.out.println(200000 % 3);

    }

    public static ListNode rotateRight2(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }

        List<Integer> buffer = new ArrayList<>();
        List<Integer> buffer2 = new ArrayList<>();
        convert2Array(buffer, buffer2, head);


        for (int i = 0; i < buffer.size(); i++) {
            buffer.set((i + k) % buffer.size(), buffer2.get(i));
        }

        return convert2List(buffer);
    }

    private static void convert2Array(List<Integer> buffer, List<Integer> buffer2, ListNode head) {
        while (head != null) {
            buffer.add(head.val);
            buffer2.add(head.val);
            head = head.next;
        }
    }

    private static ListNode convert2List(List<Integer> buffer) {
        ListNode head = new ListNode(buffer.get(0));
        ListNode first = head;
        ListNode listNode;
        for (int i = 1; i < buffer.size(); i++) {
            listNode = new ListNode(buffer.get(i));
            head.next = listNode;
            head = head.next;
        }
        return first;
    }

    // TL
    public static ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }


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
            head = firstNode;
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
