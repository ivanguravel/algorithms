package org.ivzh.graph;

import java.util.*;


// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
public class NumberOfConnectedComponentsUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        DSU dsu = new DSU(n);

        for (int[] j : edges) {
            dsu.union(j[0], j[1]);
        }


        Set<Integer> set = new HashSet<>();

        for (int i = 0; i <= n; i++) {
            set.add(dsu.find(i));
        }

        return set.size() -1;
    }


    static class DSU {
        Map<Integer, Integer> map = new HashMap<>();


        public DSU(int n) {
            for (int i = 0; i <= n; i++) {
                map.put(i, i);
            }
        }

        public void union(int a, int b) {
            int x = find(a);
            int y = find(b);

            map.put(x, y);
        }

        public int find(int i) {

            if (map.get(i) == i) {
                return i;
            }

            return find(map.get(i));
        }
    }
}
