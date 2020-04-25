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

        if (m == 0 || n == k) {
            out.println(0);
        } else {
            Map<Integer, List<Integer>> tunnels = new HashMap<>();

            readTunnels(in, k, tunnels);

            int connectedComponents = findConnectedComponents(tunnels, n);

            out.println(n - connectedComponents - 1);
        }
    }

    int findConnectedComponents(Map<Integer, List<Integer>> tunnels, int n) {
        Integer connectedComponents = 0;
        Integer allConnectedComponents = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            if (!visited.contains(i)) {
                connectedComponents = dfs(tunnels, 0, visited, i);

                if (connectedComponents != null) {
                    allConnectedComponents = allConnectedComponents + 1;
                }
            }
        }
        return allConnectedComponents;
    }

    Integer dfs(Map<Integer, List<Integer>> tunnels, Integer connectedComponents, Set<Integer> visited, Integer start) {
        visited.add(start);
        List<Integer> values = tunnels.get(start);

        if (values != null) {
            for (Integer val : values) {
                if (!visited.contains(val)) {
                    dfs(tunnels, connectedComponents, visited, val);
                }
            }
        } else {
            connectedComponents = connectedComponents + 1;
        }
        return connectedComponents;
    }

    boolean isIslandContainsInTunnels(Map<Integer, List<Integer>> tunnels, int islandNumber) {
        boolean inKey = tunnels.containsKey(islandNumber);
        if (inKey) {
            return inKey;
        }
        for (Map.Entry<Integer, List<Integer>> e : tunnels.entrySet()) {
            if (e.getValue() != null) {
                if (e.getValue().contains(islandNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    void readTunnels(Scanner in, int count, Map<Integer, List<Integer>> tunnelss) {
        for (int i = 0; i < count; i++) {
            int one = in.nextInt();
            int two = in.nextInt();
            List<Integer> v = tunnelss.get(one);
            if (v != null) {
                v.add(two);
            } else {
                v = new LinkedList<>();
                v.add(two);
                tunnelss.put(one, v);
            }
        }
    }
}
