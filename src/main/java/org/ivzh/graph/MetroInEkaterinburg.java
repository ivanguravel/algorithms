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

        Map<Integer, Integer> tunnels = new HashMap<>();

        readTunnels(in, k, tunnels);

        int dependentGroups = findDependentGroups(tunnels, n);
        out.println(dependentGroups - 1);
    }

    int findDependentGroups(Map<Integer, Integer> tunnels, int n) {
        Integer dependentGroups = 0;
        Integer allDependentGroups = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            if (!visited.contains(i)) {
                dependentGroups = dfs(tunnels, 0, visited, i);

                if (dependentGroups != null) {
                    allDependentGroups = allDependentGroups + 1;
                }
            }
        }
        return allDependentGroups;
    }

    Integer dfs(Map<Integer, Integer> tunnels, Integer dependentGroups, Set<Integer> visited, Integer start) {
        visited.add(start);
        Integer val = tunnels.get(start);
        if (val != null && !visited.contains(val)) {
            dependentGroups = dependentGroups + 1;
            dfs(tunnels, dependentGroups, visited, val);
        }
        return ++dependentGroups;
    }

    void readTunnels(Scanner in, int count, Map<Integer, Integer> tunnelss) {
        for (int i = 0; i < count; i++) {
            int one = in.nextInt();
            int two = in.nextInt();
            tunnelss.put(one, two);
        }
    }
}
