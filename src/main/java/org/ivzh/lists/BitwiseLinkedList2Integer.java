package org.ivzh.lists;

public class BitwiseLinkedList2Integer {

    public static void main(String[] args) {
        getDecimalValue(head);
    }
    
    public int getDecimalValue(ListNode head) {
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
