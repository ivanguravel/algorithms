package org.ivzh.tree;

import java.util.*;

// https://leetcode.com/problems/balanced-binary-tree/
public class BalancedBinaryTree {


   public boolean isBalanced(TreeNode root) {
        if (root != null) {
            return Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1 && 
            isBalanced(root.left) && isBalanced(root.right);
        }  else {
            return true;
        } 
    }
    
    
    private int getHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
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
