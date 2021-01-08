package org.ivzh.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagTraversal {


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.add(root);
        }

        Integer n;
        List<Integer> l;
        TreeNode tree;
        boolean flag = false;
        while (!q.isEmpty()) {
            n = q.size();
            l = new ArrayList<>();
            while(n-- != 0) {
                tree = q.poll();
                l.add(tree.val);

                if (flag) {
                    if (tree.left != null) {
                        q.add(tree.left);
                    }
                    if (tree.right != null) {
                        q.add(tree.right);
                    }
                } else {
                    if (tree.right != null) {
                        q.add(tree.right);
                    }
                    if (tree.left != null) {
                        q.add(tree.left);
                    }
                }
            }
            result.add(l);
            flag = !flag;
        }

        return result;
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
