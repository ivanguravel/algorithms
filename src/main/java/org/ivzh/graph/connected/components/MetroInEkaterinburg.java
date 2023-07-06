package org.ivzh.graph.connected.components;

import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1272
public class MetroInEkaterinburg {
    
    
    private DSU dsu;
    private Set<Integer> uniqueParents;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new MetroInEkaterinburg().solve(in, out);

        out.flush();
    }

    void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();

        if (m == 0) {
            out.println(0);
        } else {
            Map<Integer, List<Integer>> tunnels = new HashMap<>();
            readTunnels(in, k, tunnels);
            int connectedComponents = findConnectedComponents(tunnels, n);
            out.println(connectedComponents - 1);        
            
        }
    }
    
    
    void solve2(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int m = in.nextInt();

        this.dsu = new DSU(n);
        this.uniqueParents = new HashSet<>();

        if (m == 0) {
            out.println(0);
        } else {
            readTunnels2(in, k);

            for (int i = 1; i <=n; i++) {
                this.uniqueParents.add(this.dsu.get(i));
            }

            out.print(this.uniqueParents.size() -1);
            out.flush();
        }
    }
    
    void readTunnels2(Scanner in, int count) {
        for (int i = 0; i < count; i++) {
            int one = in.nextInt();
            int two = in.nextInt();
            dsu.join(one, two);
         }
    }

    int findConnectedComponents(Map<Integer, List<Integer>> tunnels, int n) {
        Integer connectedComponents = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            if (!visited.contains(i)) {
                dfs(tunnels, i, visited);
                connectedComponents = connectedComponents + 1;
            }
        }
        return connectedComponents;
    }

    void dfs(Map<Integer, List<Integer>> tunnels, int start, Set<Integer> visited) {
        visited.add(start);
        List<Integer> values = tunnels.get(start);
        if (values != null) {
            for (Integer val : values) {
                if (!visited.contains(val)) {
                    dfs(tunnels, val, visited);
                    visited.add(val);
                }
            }
        }
    }

    void readTunnels(Scanner in, int count, Map<Integer, List<Integer>> tunnelss) {
        for (int i = 0; i < count; i++) {
            int one = in.nextInt();
            int two = in.nextInt();
            fillIn(tunnelss, one, two);
            // i did that because of graph's tests can contain bidirectional dependencies
            fillIn(tunnelss, two, one);
         }
    }

    void fillIn(Map<Integer, List<Integer>> tunnels, Integer k, Integer v) {
        List<Integer> val = tunnels.get(k);
        if (val != null) {
            val.add(v);
        } else {
            val = new LinkedList<>();
            val.add(v);
            tunnels.put(k, val);
        }
    }
    
    
    
    
    static class DSU {
        int[] parents = new int[10_001];
        int[] dsuSize = new int[10_001];

        public DSU(int n) {
            for (int i = 0; i <= n; i++) {
                dsuSize[i] = 1;
                parents[i] = i;
            }
        }

        public void join(int a, int b) {
            int parentA = get(a);
            int parentB = get(b);

            if (parentA == parentB) {
                return;
            }

            if (dsuSize[parentA] > dsuSize[parentB]) {
                swap(parentA, parentB);
            }


            dsuSize[parentB] = dsuSize[parentB] + dsuSize[parentA];

            parents[parentA] = parentB;
        }

        public int get(int vertex) {
            int parent = parents[vertex];
            if (vertex == parent) {
                return vertex;
            } else {
                return parents[vertex] = get(parent);
            }
        }

        private void swap(int a, int b) {
            int temp = a;
            a = b;
            b = temp;
        }
    }
}
