package org.ivzh.tree;

import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1471
// TODO: case 13
public class DistanceBetweenNodes {

    List<Tuple> graph[];
    int[] level;
    int[] distances;
    int dp[][], log;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new DistanceBetweenNodes().solve(in, out);
        out.flush();
    }

    public void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        graph = new ArrayList[n+1];
        dp = new int[n + 1][n];

        // black magic
        log = (int)Math.ceil(Math.log(n) / Math.log(2));

        for (int i = 0; i <= n; ++i) {
            graph[i] = new ArrayList<>(2);
        }

        this.level = new int[n + 1];
        this.distances = new int[n + 1];

        for (int i = 1; i < n; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();

            graph[v].add(new Tuple(u, w));
            graph[u].add(new Tuple(v, w));
        }

        dfs(0, 0);
        int m = in.nextInt();

        for (int i =0 ; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            System.out.println(distanceBetweenNodes(u, v));
        }
    }

    int distanceBetweenNodes(int u, int v) {
        return (distances[u] + distances[v]) - 2 * distances[lca(u, v)];
    }

    int lca(int u, int v)
    {
        if (level[u] < level[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        for (int i = log; i >= 0; i--) {
            if ((level[u] - (int)Math.pow(2, i)) >= level[v])
                u = dp[u][i];
        }

        if (u == v)
            return u;

        for (int i = log; i >= 0; i--) {
            if (dp[u][i] != dp[v][i]) {
                u = dp[u][i];
                v = dp[v][i];
            }
        }

        return dp[u][0];
    }


    void dfs(int node, int parent) {
        level[node] = level[parent] + 1;
        dp[node][0] = parent;
        for (int i = 1; i <= log; i++)
            dp[node][i] = dp[dp[node][i - 1]][i - 1];
        for (Tuple vertex : graph[node]) {
            if (vertex.node != parent) {
                distances[vertex.node] = distances[node] + vertex.distance;
                dfs(vertex.node, node);
            }
        }
    }

    static class Tuple {
        int node;
        int distance;

        public Tuple(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }
}