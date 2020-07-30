package org.ivzh.lists;

// https://leetcode.com/problems/merge-two-sorted-lists
public class Merge2SortedLists {

//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode result;
//        ListNode first = null;
//        int count = 0;
//        while (l1 != null || l2 != null) {
//            if (l1 == null) {
//                result = new ListNode(l2.val);
//                l2 = l2.next;
//                result = result.next;
//                ++count;
//            } else if (l2 == null) {
//                result = new ListNode(l1.val);
//                l1 = l1.next;
//                result = result.next;
//                ++count;
//            } else if (l1.val >= l2.val) {
//                result = new ListNode(l2.val);
//                l2 = l2.next;
//                result = result.next;
//                ++count;
//            } else {
//                result = new ListNode(l1.val);
//                l1 = l1.next;
//                result = result.next;
//                ++count;
//            }
//            if (count == 1) {
//                first = result;
//            }
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
