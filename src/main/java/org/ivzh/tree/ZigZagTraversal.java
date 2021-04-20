package org.ivzh.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://algocademy.com/app/#problem/zigzag-tree-traversal/
public class ZigZagTraversal {


    public List<List<Integer>> zigzagTraversal(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        fillInQueue(root, queue);
        
        Integer size;
        LinkedList<Integer> list;
        TreeNode tree;
        boolean flag = false;
        while (!queue.isEmpty()) {
            size = queue.size();
            list = new LinkedList<>();
            while(size-- != 0) {
                tree = queue.poll();
                fillTheResult(list, tree, flag);
                fillInQueue(tree.left, queue);
                fillInQueue(tree.right, queue);
            }
            result.add(list);
            flag = !flag;
        }

        return result;
    }
    
    private void fillTheResult(LinkedList<Integer> list, TreeNode tree, boolean flag) {
        if (flag) {
            list.addFirst(tree.val);
        } else {
            list.add(tree.val);
        }
    }
    
    private void fillInQueue(TreeNode tree, Queue<TreeNode> queue) {
        if (tree != null) {
            queue.add(tree);
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
