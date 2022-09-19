package org.ivzh.graph;

import java.io.*;
import java.util.*;

// https://leetcode.com/problems/number-of-provinces
public class NumberOfProvinces {

    private Set<Integer> visited = new HashSet<>();
    
    public int findCircleNum(int[][] c) {
        int size = 0;
        Map<Integer, List<Integer>> tunnels = new HashMap();
        
        for (int i = 0; i < c.length; i++) { 
            for (int j = 0; j < c[0].length; j++) {
                if (c[i][j] == 1) {
                    size++;
                    if (!tunnels.containsKey(i)) {
                        tunnels.put(i, new LinkedList<>());
                    }

                    tunnels.get(i).add(j);

                    if (!tunnels.containsKey(j)) {
                        tunnels.put(j, new LinkedList<>());
                    }

                    tunnels.get(j).add(i);
                }
            }
        }
        return findConnectedComponents(tunnels, size);
    }
        
        
    private int findConnectedComponents(Map<Integer, List<Integer>> tunnels, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (!visited.contains(i)) {
                dfs(tunnels, i);
                ++count;
            }
        }
        return count;
    }
    
    public void dfs(Map<Integer, List<Integer>> tunnels, int i) {
        visited.add(i);
        List<Integer> values = tunnels.get(i);
        if (values != null) {
            for (Integer val : values) {
                if (!visited.contains(val)) {
                    dfs(tunnels, val);
                    visited.add(val);
                }
            }
        }
    }
  
  
  //////////////////////////////////////////////
  
  
  

}
