package org.ivzh.tree;


import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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


    // WA: passed 18 test cases
    private List<Integer> inOrderUsingStack(TreeNode root, List<Integer> result) {
        Stack<Integer> stack = new Stack<>();
        dfsMin(root, stack);

        while (!stack.isEmpty()) {
            result.add(stack.pop());
            if (root.right !=null) {
                dfsMin(root.right, stack);
                root = root.right;
            }
        }

        return result;
    }

    private void dfsMin(TreeNode node, Stack<Integer> stack) {
        while (node != null) {
            stack.push(node.val);
            node = node.left;
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
