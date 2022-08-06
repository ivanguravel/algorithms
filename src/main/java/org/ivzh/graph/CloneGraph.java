package org.ivzh.graph;

import java.io.*;
import java.util.*;

// https://leetcode.com/problems/clone-graph
public class CloneGraph {
    Map<Integer,Node> map = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    Queue<Node> q = new ArrayDeque<>();
    
    public Node bfsCloneGraph(Node node) {
        
        if (node == null) {
            return null;
        }
        
        q.add(node);
        while (!q.isEmpty()) {
            Node current = q.poll();
            visited.add(current.val);
            Node copy = map.getOrDefault(current.val, new Node(current.val));
            map.put(current.val, copy);
            for (Node n : current.neighbors) {
                Node copyN = map.getOrDefault(n.val, new Node(n.val));
                map.put(copyN.val, copyN);
                copy.neighbors.add(copyN);
                
                if (!visited.contains(n.val)) {
                    q.add(n);
                    visited.add(n.val);

                }
            }
            
        }
        return map.get(node.val);
    }
    
    private void dfs(Node node) {
        if (visited.contains(node.val)) {
            return;
        }
        
        Node clone = map.getOrDefault(node.val, new Node(node.val));
        map.put(node.val, clone);
        
        visited.add(node.val);
        
        for (Node n : node.neighbors) {
            Node cloneN = map.getOrDefault(n.val, new Node(n.val));
            
            map.put(cloneN.val, cloneN);
            
            dfs(n);
            clone.neighbors.add(cloneN);
        }
    }
  
  
  static class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

}
