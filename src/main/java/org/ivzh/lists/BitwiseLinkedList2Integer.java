package org.ivzh.lists;


// https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
public class BitwiseLinkedList2Integer {

    public static void main(String[] args) {

        getDecimalValue(new ListNode(1));
    }
    
    public static int getDecimalValue(ListNode head) {
        String result = "";
        while (head != null) {
            result = result + head.val;
            head = head.next;
        }
        return Integer.parseInt(result, 2);
    }
    
 public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
}
