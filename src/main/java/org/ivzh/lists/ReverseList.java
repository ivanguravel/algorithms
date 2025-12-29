package org.ivzh.lists;

// https://leetcode.com/problems/reverse-linked-list/
public class ReverseList {


    public ListNode reverseList(ListNode head) {
        ListNode previous = null;
        ListNode next = null;
        ListNode current = head;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }


    public ListNode reverseListTwo(ListNode head) {
        return reverseListRec(head, null);
    }


    public static ListNode reverseListRec(ListNode head, ListNode prev) {
        if (head == null) {
          return prev;
        }
    
        ListNode next = head.next;
        head.next = prev;

        
        return reverseListRec(next, head);
    }
    

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
