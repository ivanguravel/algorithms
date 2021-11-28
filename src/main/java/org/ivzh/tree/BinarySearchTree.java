package org.ivzh.tree;

public class BinarySearchTree {

    int val;
    BinarySearchTree left;
    BinarySearchTree right;


    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree(4);

        insert(0, bst);
        insert(1, bst);
        insert(5, bst);
        insert(6, bst);


        System.out.println(contains(1, bst));
        System.out.println(contains(0, bst));
        System.out.println(contains(5, bst));
        System.out.println(contains(6, bst));
        System.out.println(contains(8, bst));

        System.out.println(maxHeight(bst));

    }


    public BinarySearchTree(int val) {
        this.val = val;
    }

    public void setLeft(BinarySearchTree left) {
        this.left = left;
    }

    public void setRight(BinarySearchTree right) {
        this.right = right;
    }

    public static boolean contains(int v, BinarySearchTree bst) {
        if (bst == null) {
            return false;
        } else if (bst.val == v) {
            return true;
        } else if (v < bst.val) {
            return contains(v, bst.left);
        } else {
            return contains(v, bst.right);
        }
    }

    public static void insert(int v, BinarySearchTree bst) {
        if (bst == null) {
            return;
        }

        if (bst.left == null && v < bst.val) {
            bst.left = new BinarySearchTree(v);
        }

        if (bst.right == null && v > bst.val) {
            bst.right = new BinarySearchTree(v);
        }

         if (v < bst.val) {
            insert(v, bst.left);
        } else {
            insert(v, bst.right);
        }
    }

    public static int maxHeight(BinarySearchTree bst) {
        int l = heightLeft(bst, 0, true);
        int r = heightLeft(bst, 0, false);
        return Math.max(l, r);
    }


    private static int heightLeft(BinarySearchTree bst, int h, boolean leftOrRight) {
        if (bst != null) {
            h = h +1;
            heightLeft( leftOrRight ? bst.left : bst.right, h, leftOrRight);
        }
        return h + 1;
    }
}
