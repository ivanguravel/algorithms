package org.ivzh.graph;

import java.util.*;

// https://leetcode.com/problems/n-ary-tree-level-order-traversal/
public class NaryTreeLevelOrderTraversal {

//  public List<List<Integer>> levelOrder(Node root) {
//        List<List<Integer>> result = new LinkedList<>();
//        Queue<Node> q = new LinkedList<>();
//        if (root !=null) {
//            q.add(root);
//        }
//
//        List<Integer> l;
//        Node it;
//        while(!q.isEmpty()) {
//           l = new ArrayList<>();
//           int level = q.size();
//           while(level-- != 0) {
//               it = q.poll();
//               l.add(it.val);
//
//               if (it.children != null) {
//                   for (Node n : it.children) {
//                       q.add(n);
//                   }
//               }
//           }
//
//           result.add(l);
//
//        }
//
//        return result;
//    }
//
}
