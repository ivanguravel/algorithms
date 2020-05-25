package org.ivzh.tree;

import java.util.LinkedList;
import java.util.List;


// https://leetcode.com/problems/all-elements-in-two-binary-search-trees
public class AllElementsInTwoBinarySearchTrees {

    List<Integer> list = new LinkedList<>();

    public List<Integer> getAllElements(DistanceBeetweenNodesInBST.TreeNode root1, DistanceBeetweenNodesInBST.TreeNode root2) {
        List<Integer> first =  new LinkedList<>();
        List<Integer> second = new LinkedList<>();

        fillIn(first, root1);
        fillIn(second, root2);

        int idx1 = 0;
        int idx2 = 0;

        while (idx1 < first.size() && idx2 < second.size()) {
            if (first.get(idx1) < second.get(idx2)) {
                list.add(first.get(idx1));
                ++idx1;
            } else if (first.get(idx1) >= second.get(idx2)) {
                list.add(second.get(idx2));
                ++idx2;
            }
        }

        if(idx1<first.size())
        {
            while(idx1<first.size()){
                list.add(first.get(idx1));
                idx1++;
            }
        }
        else
        {
            while(idx2<second.size()){
                list.add(second.get(idx2));
                idx2++;
            }
        }

        return list;
    }

    public void fillIn(List<Integer> l, DistanceBeetweenNodesInBST.TreeNode root) {
        if (root != null) {
            fillIn(l, root.left);
            l.add(root.val);
            fillIn(l, root.right);
        }
    }


}
