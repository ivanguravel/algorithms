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

        readGraph(in, k, tunnels);

        int dependentGroups = findDependentGroups(tunnels, n);
        out.println(dependentGroups - 1);
    }

    int findDependentGroups(Map<Integer, Integer> tunnels, int n) {
        int dependentGroups = 0;
        int allDependentGroups = 0;
        Set<Integer> visited = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            dependentGroups = dfs(tunnels, dependentGroups, visited, i);

            if (dependentGroups != 0) {
                allDependentGroups++;
                dependentGroups = 0;
            }
        }
        return allDependentGroups;
    }

    Integer dfs(Map<Integer, Integer> tunnels, Integer dependentGroups, Set<Integer> visited, Integer forFind) {
        throw new UnsupportedOperationException();
    }

    void readGraph(Scanner in, int count, Map<Integer, Integer> tunnelss) {
        for (int i = 0; i < count; i++) {
            int one = in.nextInt();
            int two = in.nextInt();
            tunnelss.put(one, two);
        }
    }
}
