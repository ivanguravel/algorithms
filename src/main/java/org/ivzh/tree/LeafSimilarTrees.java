package org.ivzh.tree;

import java.util.*;

// https://leetcode.com/problems/leaf-similar-trees/description/
public class LeafSimilarTrees {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> listDataOne = new LinkedList<>();
        List<Integer> listDataOTwo = new LinkedList<>();
        dfs(root1, listDataOne);
        dfs(root2, listDataOTwo);
        return listDataOne.equals(listDataOTwo);
    }

    private void dfs(TreeNode root, List<Integer> listData) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                listData.add(root.val);
            } else {
                dfs(root.left, listData);
                dfs(root.right, listData);
            }
        }
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
