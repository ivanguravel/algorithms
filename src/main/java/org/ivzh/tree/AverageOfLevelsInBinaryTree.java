package org.ivzh.tree;

import java.util.*;


// https://leetcode.com/problems/average-of-levels-in-binary-tree/description/
public class AverageOfLevelsInBinaryTree {

    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Double> list = new LinkedList<>();
        q.add(root);
        TreeNode buffer;
        while(!q.isEmpty()) {
            int size = q.size();

            double sum = 0.0;
            for (int i = 0; i < size; i++) {
                buffer = q.poll();
                sum += buffer.val;

                if (buffer.left != null) {
                    q.add(buffer.left);
                }

                if (buffer.right != null) {
                    q.add(buffer.right);
                }
            }

            list.add(sum / size);
        }

        return list;
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
