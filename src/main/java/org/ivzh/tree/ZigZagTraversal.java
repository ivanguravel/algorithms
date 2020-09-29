package org.ivzh.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagTraversal {


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        zigzag(root, result);
        return result;
    }


    private void zigzag(TreeNode root, List<List<Integer>> result) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> forAdd;
        if (root != null) {
            q.add(root);
            forAdd = new LinkedList<>();
            forAdd.add(root.val);
            result.add(forAdd);
        }
        Integer level = 2;
        TreeNode l;

        while (!q.isEmpty()) {

            l = q.poll();

            TreeNode left = l.left;
            TreeNode right = l.right;
            forAdd = new LinkedList<>();

            Integer leftVal = null;
            Integer rightVal = null;

            if (right !=null) {
                rightVal =  right.val;


                q.add(right);
            }

            if (left !=null) {
                leftVal =  left.val;

                q.add(left);
            }


            if (level % 2 == 0) {
                if (rightVal != null) {
                    forAdd.add(rightVal);
                }
                if (leftVal != null) {
                    forAdd.add(leftVal);
                }
            } else {
                if (leftVal != null) {
                    forAdd.add(leftVal);
                }
                if (rightVal != null) {
                    forAdd.add(rightVal);
                }
            }
            level = level +1;
            if (!forAdd.isEmpty()) {
                result.add(forAdd);
            }
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
