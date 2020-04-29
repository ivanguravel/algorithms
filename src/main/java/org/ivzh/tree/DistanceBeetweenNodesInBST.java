package org.ivzh.tree;

public class DistanceBeetweenNodesInBST {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.left.right = new TreeNode(8);

        System.out.println("Dist(4, 5) = "+answer(root, 4, 5));
        System.out.println("Dist(4, 6) = "+answer(root, 4, 6));
        System.out.println("Dist(3, 4) = "+answer(root, 3, 4));
        System.out.println("Dist(2, 4) = "+answer(root, 2, 4));
        System.out.println("Dist(8, 5) = " +answer(root, 8, 5));
    }

    static int answer(TreeNode root, int n1, int n2) {
        int firstDepth = dfsPathLengthCount(root, n1) - 1;
        int secondDepth = dfsPathLengthCount(root, n2) - 1;
        TreeNode lca = lca(root, n1, n2);
        int lcaDistance = dfsPathLengthCount(root, lca.val) - 1;
        return (firstDepth + secondDepth) - 2 * lcaDistance;
    }


    static TreeNode lca(TreeNode root, int n1, int n2) {
        if (root == null) {
            return null;
        }

        TreeNode lca = null;

        if (root.val == n1 || root.val == n2) {
            lca = root;
        }

        TreeNode left = lca(root.left, n1, n2);
        TreeNode right = lca(root.right, n1, n2);

        if (left != null && right != null) {
            lca = root;
        } else if (left != null) {
            lca = left;
        } else if (right != null) {
            lca = right;
        }

        return lca;
    }

    static int dfsPathLengthCount(TreeNode root, int n1) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        int countFromLeft  = dfsPathLengthCount(root.left, n1);
        int countFromRight  = dfsPathLengthCount(root.left, n1);

        if (root.val == n1) {
            return count + 1;
        } else if (countFromLeft > 0) {
            return countFromLeft + 1;
        } else if (countFromRight > 0) {
            return countFromRight + 1;
        } else {
            return 0;
        }
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

