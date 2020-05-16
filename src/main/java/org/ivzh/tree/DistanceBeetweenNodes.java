package org.ivzh.tree;


import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1471
public class DistanceBeetweenNodes {

    List<Integer> graph[];
    int[] lev;
    int memo[][], log;

    List<Integer> result;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new DistanceBeetweenNodes().solve(in, out);
        out.flush();
    }

    public void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        graph = new ArrayList[n+1];
        memo = new int[n + 1][20];

        // black magic
        log = (int)Math.ceil(Math.log(n) / Math.log(2));

        for (int i = 0; i <= n; ++i) {
            graph[i] = new ArrayList<>();
        }

        this.lev = new int[n + 1];

        for (int i = 1; i < n; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            graph[w].add(v);
            graph[w].add(u);
        }

        dfs(1, 1);
        int m = in.nextInt();
        this.result = new ArrayList<>(m + 1);

        for (int i =0 ; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            result.add(distanceBetweenNodes(u, v));
        }

        for (Integer i : result) {
            System.out.println(i);
        }
    }

    int distanceBetweenNodes(int u, int v) {
        int distanceOne = lev[u];
        int distanceTwo = lev[v];
        return distanceOne + distanceTwo - 2 * lca(u, v);
    }

    int lca(int u, int v)
    {
        // if v deeper than u - change placement
        if (lev[u] < lev[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // let's call it binary search
        for (int i = log; i >= 0; i--) {
            if ((lev[u] - (int)Math.pow(2, i)) >= lev[v])
                u = memo[u][i];
        }

        if (u == v) {
            return u;
        }

        //
        for (int i = log; i >= 0; i--) {
            if (memo[u][i] != memo[v][i]) {
                u = memo[u][i];
                v = memo[v][i];
            }
        }

        return memo[u][0];
    }


    // Pre-processing to calculate values of memo[][]
    void dfs(int u, int p) {
        // Using recursion formula to calculate
        // the values of memo[][]
        memo[u][0] = p;
        for (int i = 1; i <= log; i++)
            memo[u][i] = memo[memo[u][i - 1]][i - 1];
        for (int v : graph[u]) {
            if (v != p) {
                // Calculating the level of each node
                lev[v] = lev[u] + 1;
                dfs(v, u);
            }
        }
    }
}
