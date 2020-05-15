package org.ivzh.tree;

import org.ivzh.graph.MetroInEkaterinburg;

import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1471
public class DistanceBeetweenNodes {

    List<GraphNode>[] graph;
    int[][] pairs;
    int[] distances;
    int[] depth;
    int root;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new DistanceBeetweenNodes().solve(in, out);

        out.flush();
    }

    public void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        List<GraphNode>[] graph = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }
        this.pairs = new int[n][17];
        this.distances = new int[n];
        this.depth = new int[n];
        this.root = 0;
        for (int i = 1; i < n; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            graph[u].add(new GraphNode(v, w));
            graph[v].add(new GraphNode(u, w));
        }
    }

    void dfs(int vertex, int high, int level, int newDistance) {
        distances[vertex] = newDistance;
        depth[vertex] = level;
        pairs[vertex][0] = vertex == root ? vertex : high;
        for (GraphNode e : graph[vertex]) {
            if (e.vertex != high) {
                dfs(e.vertex, vertex, level + 1, newDistance + e.weight);
            }
        }
    }


    class GraphNode {
        int vertex;
        int weight;

        public GraphNode(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

}
