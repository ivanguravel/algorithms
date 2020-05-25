package org.ivzh.tree;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/validate-binary-search-tree/
public class ValidateBinarySearchTree {

    public boolean isValidBST(DistanceBeetweenNodesInBST.TreeNode root) {
        List<Integer> list =  new ArrayList<>();
        fillIn(list, root);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) >= list.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void fillIn(List<Integer> l, DistanceBeetweenNodesInBST.TreeNode root) {
        if (root != null) {
            fillIn(l, root.left);
            l.add(root.val);
            fillIn(l, root.right);
        }
    }
}
