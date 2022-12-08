package org.ivzh.tree;

import java.util.*;


// https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
public class ConvertSortedListToBinarySearchTree {
  
    List<Integer> list;

    public TreeNode sortedListToBST(ListNode head) {
        list = new ArrayList<>();
        while (head!=null) {
            list.add(head.val);
            head = head.next;
        }
        return dfs(0, list.size()-1);
    }

    public TreeNode dfs(int left, int right) {
        if (left > right) return null;

        int p = (left + right) / 2;

        TreeNode root = new TreeNode(list.get(p));
        root.left = dfs(left, p - 1);
        root.right = dfs(p + 1, right);
        return root;
    }
}
