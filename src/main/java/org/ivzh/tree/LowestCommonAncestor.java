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
        List<TreeNode> path2a = new LinkedList<>();
        List<TreeNode> path2b = new LinkedList<>();

        path2Node(binaryTree, a, path2a);
        path2Node(binaryTree, b, path2b);

        Integer c = 0;

        TreeNode treeNode = path2a.get(c);
        TreeNode treeNode2 = path2b.get(c);

        if (checkIfOneTreeNodeIsAncestor(path2a, b) != null) {
            return checkIfOneTreeNodeIsAncestor(path2a, b);
        }

        if (checkIfOneTreeNodeIsAncestor(path2b, a) != null) {
            return checkIfOneTreeNodeIsAncestor(path2b, a);
        }

        while (path2a.size() > c && path2b.size() > c && treeNode.val == treeNode2.val) {
            treeNode = path2a.get(c);
            treeNode2 = path2b.get(c);
            c = c + 1;
        }
        return treeNode;
    }

    private static TreeNode checkIfOneTreeNodeIsAncestor(List<TreeNode> path, int a) {
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i).val == a && i < (path.size() - 1)) {
                return path.get(i);
            }
        }
        return null;
    }

    private static void path2Node(TreeNode binaryTree, int a, List<TreeNode> forFeel) {
        if (binaryTree != null) {
            int val = binaryTree.val;
            if (a == val) {
                return;
            } else if (a < val) {
                forFeel.add(binaryTree);
                path2Node(binaryTree.left, a, forFeel);
            } else {
                forFeel.add(binaryTree);
                path2Node(binaryTree.right, a, forFeel);
            }
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
