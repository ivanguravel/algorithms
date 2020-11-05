package org.ivzh.graph;

import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1080
public class MapColoring {

    int n;
    int[] colors;
    Map<Integer, List<Integer>> graph;
    Set<Integer> visited;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new MapColoring().solve(in, out);

        out.flush();
    }

    private void solve(Scanner in, PrintWriter out) {
        readData(in);
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited.contains(i)) {
                result = dfs(i, 0);
            }
        }
        if (result == -1) {
            out.println(result);
        } else {
            printResult(out);
        }
        out.flush();
    }

    private void readData(Scanner in) {
        visited = new HashSet<>();
        graph = new HashMap<>();

        n = in.nextInt();
        colors = new int[n+1];
        List<Integer> neighbors;
        Integer value;


        for (int i = 1; i <= n; i++) {
            neighbors = graph.get(i);
            if (neighbors == null) {
                neighbors = new LinkedList<>();
            }
            value = in.nextInt();
            while (value != 0) {
                neighbors.add(value);
                graph.put(i, neighbors);
                value = in.nextInt();
            }
        }
    }

    private int dfs(int vertex, int color) {
        visited.add(vertex);
        colors[vertex] = color % 2 != 0 ? 1 : 0;
        List<Integer> neighbors = graph.get(vertex);
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, ++color);
                } else if (colors[neighbor] == colors[vertex]) {
                    return -1;
                }
            }
        }
        return 0;
    }

    private void printResult(PrintWriter out) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i<= n; i++) {
            builder.append(colors[i]);
        }
        out.println(builder.toString());
    }
}
