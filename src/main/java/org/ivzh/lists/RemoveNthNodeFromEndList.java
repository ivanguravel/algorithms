package org.ivzh.lists;


import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
public class RemoveNthNodeFromEndList {

  public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = head;
        int l = 0;
        while (node != null) {
            ++l;
            node = node.next;
        }
        
        int i = 0;
        node = head;
        while (i < (l - n - 1)) {
            ++i;
            node = node.next;
        }
               
        node.next = node.next.next;
        return head;
    }
}
