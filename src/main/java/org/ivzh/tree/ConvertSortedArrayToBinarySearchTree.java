package org.ivzh.tree;

import java.util.*;


// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
public class ConvertSortedArrayToBinarySearchTree {

    int[] nums;

    public TreeNode dfs(int left, int right) {
        if (left > right) return null;

        // always choose left middle node as a root
        int p = (left + right) / 2;

        // preorder traversal: node -> left -> right
        TreeNode root = new TreeNode(nums[p]);
        root.left = dfs(left, p - 1);
        root.right = dfs(p + 1, right);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return dfs(0, nums.length - 1);
    }

}
