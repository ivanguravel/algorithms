package org.ivzh.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
public class MaximumLevelSumBinaryTree {

    public int maxLevelSum(TreeNode root) {

        List<Integer> sums = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        q.add(root);

        while(!q.isEmpty()) {
            int size = q.size();
            int sum = 0;

            for (int i=0; i<size; i++) {
                TreeNode temp = q.poll();
                sum = sum + temp.val;

                if(temp.left != null)
                    q.add(temp.left);

                if(temp.right != null)
                    q.add(temp.right);
            }
            sums.add(sum);
            max = Math.max(max,sum);
        }
        return sums.indexOf(max)+1;
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
