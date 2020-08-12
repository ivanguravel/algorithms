package org.ivzh.lists;

// https://leetcode.com/problems/partition-list/
class PartitionList {
    public ListNode partition(ListNode head, int x) {
      
        
        ListNode left = new ListNode(0);
        ListNode right = new ListNode(0);
        
        ListNode first = left;
        ListNode second = right;
        
        while (head != null) {
            if(head.val < x){
                left.next = head;
                head = head.next;
                left = left.next;
                left.next = null;
            } else {
                right.next = head;
                head = head.next;
                right = right.next;
                right.next = null;
            }
        }
        
        left.next = second.next;
        return first.next;
    }   
  
}
