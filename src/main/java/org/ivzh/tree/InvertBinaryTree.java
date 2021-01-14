package org.ivzh.tree;

// https://leetcode.com/problems/invert-binary-tree/
public class InvertBinaryTree {

    
    public TreeNode invertTree(TreeNode root) {
        inventor(root);
        return root;
    }

    private void inventor(TreeNode root) {
        if (root != null) {
            TreeNode buf = root.left;
            root.left = root.right;
            root.right = buf;
            invertTree(root.left);
            invertTree(root.right);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
}
