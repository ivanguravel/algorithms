package org.ivzh.lists;

// https://leetcode.com/problems/merge-two-sorted-lists
public class Merge2SortedLists {

//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode node = null;
//        ListNode pointer = null;
//        ListNode first = null;
//        while(l1 != null || l2 != null) {
//            if (l1 == null) {
//                pointer = new ListNode(l2.val);
//                if (l2 != null) {
//                    l2 = l2.next;
//                }
//            } else if (l2 == null) {
//                pointer = new ListNode(l1.val);
//                if (l1 != null) {
//                    l1 = l1.next;
//                }
//            } else if (l1.val >= l2.val) {
//                pointer = new ListNode(l2.val);
//                if (l2 != null) {
//                    l2 = l2.next;
//                }
//            } else {
//                pointer = new ListNode(l1.val);
//                if (l1 != null) {
//                    l1 = l1.next;
//                }
//            }
//
//            if (node != null) {
//                node.next = pointer;
//            } else {
//                first = pointer;
//            }
//            node = pointer;
//
//
//        }
//        return first;
//    }


    public static class ListNode {
        int val;
        MergeKSortedLists.ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, MergeKSortedLists.ListNode next) { this.val = val; this.next = next; }
    }
}
