package org.ivzh.lists;


// https://leetcode.com/problems/merge-k-sorted-lists/
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) {
            return null;
        }
        System.out.println(lists.length);
        ListNode result = mergeTwoLists(null, lists[0]);
        for (int i = 1; i < lists.length; i++) {
            result =  mergeTwoLists(result, lists[i]);
        }
        return result;
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = null;
        ListNode pointer = null;
        ListNode first = null;
        while(l1 != null || l2 != null) {
            if (l1 == null) {
                pointer = new ListNode(l2.val);
                if (l2 != null) {
                    l2 = l2.next;
                }
            } else if (l2 == null) {
                pointer = new ListNode(l1.val);
                if (l1 != null) {
                    l1 = l1.next;
                }
            } else if (l1.val >= l2.val) {
                pointer = new ListNode(l2.val);
                if (l2 != null) {
                    l2 = l2.next;
                }
            } else {
                pointer = new ListNode(l1.val);
                if (l1 != null) {
                    l1 = l1.next;
                }
            }

            if (node != null) {
                node.next = pointer;
            } else {
                first = pointer;
            }
            node = pointer;


        }
        return first;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
