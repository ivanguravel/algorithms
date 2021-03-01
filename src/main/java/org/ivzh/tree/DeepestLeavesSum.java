package org.ivzh.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/deepest-leaves-sum/
public class DeepestLeavesSum {

    public int deepestLeavesSum(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root !=null) {
            q.add(root);
        }


        List<Integer> l;
        TreeNode it;
        while(!q.isEmpty()) {
            l = new ArrayList<>();
            int level = q.size();
            while(level-- != 0) {
                it = q.poll();
                l.add(it.val);

                if (it.left != null) {
                    q.add(it.left);
                }
                if (it.right != null) {
                    q.add(it.right);
                }
            }

            result.add(l);

        }

        List<Integer> endLevel = result.getLast();
        int sum = 0;
        for (Integer i : endLevel) {
            sum = sum + i;
        }
        return sum;
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
