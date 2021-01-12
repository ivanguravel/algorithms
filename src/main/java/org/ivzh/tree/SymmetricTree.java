package org.ivzh.tree;

import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/symmetric-tree/
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        TreeNode left;
        TreeNode right;
        while(!q.isEmpty()) {
            left = q.poll();
            right = q.poll();

            if (left == null && right == null) {
                continue;
            } else if (left == null || right == null) {
                return false;
            } else if (left.val != right.val) {
                return false;
            } else {
                q.add(left.left);
                q.add(right.right);
                q.add(left.right);
                q.add(right.left);
            }

        }
        return true;
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
