package org.ivzh.graph;

import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1272
public class MetroInEkaterinburg {
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
            // i did that because of graphs can contain bidirectional dependencies
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
}
