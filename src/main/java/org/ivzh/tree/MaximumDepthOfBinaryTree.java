package org.ivzh.tree;

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        return traverse(root, 0);
    }

    private int traverse(TreeNode root, int depth) {
        if (root != null) {
            return Math.max(traverse(root.left, depth) + 1, traverse(root.right, depth) + 1) ;
        } else {
            return 0;
        }
    }
    
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        int count = 0;
        q.add(root);

        while(!q.isEmpty()) {
            int size = q.size();
            count++;

            for (int i=0; i<size; i++) {
                TreeNode temp = q.poll();

                if (temp == null) {
                    continue;
                }

                if(temp.left != null)
                    q.add(temp.left);

                if(temp.right != null)
                    q.add(temp.right);
            }
        }
        return count;
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
