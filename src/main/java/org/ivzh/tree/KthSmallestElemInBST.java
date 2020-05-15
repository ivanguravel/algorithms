package org.ivzh.tree;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
public class KthSmallestElemInBST {

    List<Integer> result = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
        fillArr(root);
        return result.get(k - 1);
    }

    void fillArr(TreeNode root) {
        if (root != null) {
            fillArr(root.left);
            result.add(root.val);
            fillArr(root.right);
        }
    }


    public class TreeNode {
         int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
          }
      }

}
