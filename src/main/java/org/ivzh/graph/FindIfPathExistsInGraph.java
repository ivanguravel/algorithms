package org.ivzh.graph;


import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/find-if-path-exists-in-graph/
public class FindIfPathExistsInGraph {

    public boolean validPath(int n, int[][] edges, int s, int d) {
        DSU dsu = new DSU(n);

        for (int[] j : edges) {
            dsu.union(j[0], j[1]);
        }

        return dsu.find(s) == dsu.find(d);
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
