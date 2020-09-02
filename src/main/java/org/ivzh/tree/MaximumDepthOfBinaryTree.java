package org.ivzh.tree;

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        return traverse(root, 0);
    }

    private int traverse(TreeNode root, int depth) {
        if (root != null) {
            return Math.max(traverse(root.left, depth) + 1, traverse(root.right, depth) + 1) ;
        } else {
            return 0;
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
