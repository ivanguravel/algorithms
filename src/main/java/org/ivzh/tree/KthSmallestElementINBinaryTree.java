package org.ivzh.tree;

import java.util.LinkedList;
import java.util.List;


// https://leetcode.com/problems/all-elements-in-two-binary-search-trees
public class KthSmallestElementINBinaryTree {
  
  public int kthSmallest(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res.get(k-1);
    }
    
    private void dfs(TreeNode root, List<Integer> list) {
        if (root!= null) {
            dfs(root.left, list);
            list.add(root.val);
            dfs(root.right, list);
        }
    }
}
