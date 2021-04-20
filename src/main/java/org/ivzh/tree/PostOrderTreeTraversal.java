package org.ivzh.tree;


import java.util.LinkedList;
import java.util.List;

// https://algocademy.com/app/#problem/post-order-tree-traversal
public class PostOrderTreeTraversal {

    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        fillIn(root, result);
        return result;
    }

    private void fillIn(TreeNode root, List<Integer> result) {
        if (root != null) {
            fillIn(root.left, result);
            fillIn(root.right, result);
            result.add(root.val);
        }
    }
    
    private void preOrder(TreeNode root, List<Integer> result) {
        if (root != null) {
            result.add(root.val);
            fillIn(root.left, result);
            fillIn(root.right, result);            
        }
    }
    
    private void inOrder(TreeNode root, List<Integer> result) {
        if (root != null) {            
            fillIn(root.left, result);
            result.add(root.val);
            fillIn(root.right, result);            
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
