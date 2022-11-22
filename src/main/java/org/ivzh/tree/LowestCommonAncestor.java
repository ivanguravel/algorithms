package org.ivzh.tree;

import java.util.LinkedList;
import java.util.List;

// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;

        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
    
    public TreeNode lowestCommonAncestorBfs(TreeNode root, TreeNode p, TreeNode q) {
        int pVal = p.val;

        int qVal = q.val;

        TreeNode node = root;

        while (node != null) {

            int parentVal = node.val;

            if (pVal > parentVal && qVal > parentVal) {
                node = node.right;
            } else if (pVal < parentVal && qVal < parentVal) {
                node = node.left;
            } else {
                return node;
            }
        }
        return null;
    }


    public TreeNode lowestCommonAncestorNaive(TreeNode root, TreeNode p, TreeNode q) {

        if (q == null) {
            return p;
        }

        if (p == null) {
            return q;
        }
        return lca(root, p.val, q.val);
    }

    private static TreeNode lca(TreeNode binaryTree, int a, int b) {
        if (root == null || p == null || q == null) {
            return root;
        }
        List<TreeNode> listP = new ArrayList<>();
        List<TreeNode> listQ = new ArrayList<>();
        findNode(root, p, listP);
        findNode(root, q, listQ);
        int size = Math.min(listP.size(), listQ.size());
        TreeNode parent = root;
        for (int i = 0; i < size; i++) {
            if (listP.get(i).val == listQ.get(i).val) {
                parent = listP.get(i);
            } else {
                return parent;
            }
        }
        return parent;
    }
    
    private void findNode(TreeNode node, TreeNode target, List<TreeNode> list) {
        while (node != null) {
            list.add(node);
            if (node.val == target.val) return;
            if (node.val > target.val) node = node.left;
            else node = node.right;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
