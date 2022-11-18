package org.ivzh.lists;

import java.util.*;

// https://leetcode.com/problems/linked-list-cycle-ii/
class LinkedListCycle {

    public ListNode detectCycle(ListNode head) {
        if (head==null || head.next==null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
               while(head!=slow){
                   head=head.next;
                   slow=slow.next;
               }
               return slow;
            }
        }
        
        return null;
    }
    
    public ListNode detectCycle2(ListNode head) {
        Set<Integer> set = new HashSet<>();
        
        while (head != null) {
            if (set.contains(head.val)) {
                return head;
            }
            
            set.add(head.val);
            head = head.next;
        } 
        
        return null;
    }
}
