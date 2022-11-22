package org.ivzh.tree;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/validate-binary-search-tree/
public class ValidateBinarySearchTree {

    public boolean isValidBST(DistanceBeetweenNodesInBST.TreeNode root) {
        List<Integer> list =  new ArrayList<>();
        fillIn(list, root);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) >= list.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void fillIn(List<Integer> l, DistanceBeetweenNodesInBST.TreeNode root) {
        if (root != null) {
            fillIn(l, root.left);
            l.add(root.val);
            fillIn(l, root.right);
        }
    }
    
     public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Integer prev = null;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null && root.val <= prev) {
                return false;
            }
            prev = root.val;
            root = root.right;
        }
        return true;
    }
    
    
     Integer prev = null;
    boolean result = true;
    
    public boolean isValidBST3(TreeNode root) {
        if (!result ) {
            return false;
        }
        
        if (root!= null) {
            isValidBST(root.left);
            if (prev == null) {
                prev = root.val;
            } else {
                if (prev != null && prev >= root.val) {
                    result = false;
                }
                prev = root.val;
            }
            isValidBST(root.right);
        }
        return result;
    }
}
