package org.ivzh.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://algocademy.com/app/#problem/level-order-traversal/
// https://leetcode.com/problems/binary-tree-level-order-traversal/
public class LevelOrderTreeTraversal {

    public List<List<Integer>> levelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root !=null) {
            queue.add(root);
        }


        List<Integer> list;
        TreeNode it;
        while(!queue.isEmpty()) {
            list = new ArrayList<>();
            int level = queue.size();
            while(level-- != 0) {
                it = queue.poll();
                list.add(it.val);

                if (it.left != null) {
                    queue.add(it.left);
                }
                if (it.right != null) {
                    queue.add(it.right);
                }
            }

            result.add(list);

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
