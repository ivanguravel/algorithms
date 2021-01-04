package org.ivzh.tree;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {
  
  public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root !=null) {            
            q.add(root);
        }
        

        List<Integer> l;
        TreeNode it;
        while(!q.isEmpty()) {
            l = new ArrayList<>();
           int level = q.size();           
           while(level-- != 0) {
               it = q.poll(); 
               l.add(it.val);
               
               if (it.left != null) {
                   q.add(it.left);
               }
               if (it.right != null) {
                   q.add(it.right);
               }
           }                        
             
           result.add(l);
           
        }
        
        return result;           
        
    } 

}
