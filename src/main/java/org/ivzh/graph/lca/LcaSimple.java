package org.ivzh.graph.lca;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LcaSimple {

    public static void main(String[] args) {
        TreeNode binaryTree = createBinaryTree();
        TreeNode lca = lca(binaryTree, 5, 55);
        System.out.println(lca.val);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private static TreeNode lca(TreeNode binaryTree, int a, int b) {
        List<TreeNode> path2a = new LinkedList<>();
        List<TreeNode> path2b = new LinkedList<>();

        path2Node(binaryTree, a, path2a);
        path2Node(binaryTree, b, path2b);

        Integer c = 0;
        TreeNode res = null;
        while (path2a.size() > c && path2b.size() > c) {
            if (path2a.get(c).val == path2b.get(c).val) {
                res = path2a.get(c);
            } else {
                break;
            }
            c = c + 1;
        }
        return res;
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

    private static TreeNode createBinaryTree()
    {

        TreeNode rootNode =new TreeNode(40);
        TreeNode node20=new TreeNode(20);
        TreeNode node10=new TreeNode(10);
        TreeNode node30=new TreeNode(30);
        TreeNode node60=new TreeNode(60);
        TreeNode node50=new TreeNode(50);
        TreeNode node70=new TreeNode(70);
        TreeNode node5=new TreeNode(5);
        TreeNode node55=new TreeNode(55);

        rootNode.left=node20;
        rootNode.right=node60;

        node20.left=node10;
        node20.right=node30;

        node60.left=node50;
        node60.right=node70;

        node10.left=node5;
        node50.right=node55;

        return rootNode;
    }
}
