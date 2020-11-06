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

        for (int i = 1; i <= n; i++) {
            if (!visited.contains(i)) {
               if (!dfs(i, 0)) {
                    out.print(-1);
                    out.flush();
                    return;
                }
            }
        }
        printResult(out);
    }

    private void readData(Scanner in) {
        visited = new HashSet<>();
        graph = new HashMap<>();

        n = in.nextInt();
        colors = new int[n+1];

        Arrays.fill(colors, -1);
        List<Integer> neighbors;
        List<Integer> otherNeighbors;
        Integer value;


        for (int i = 1; i <= n; i++) {
            neighbors = graph.get(i);
            if (neighbors == null) {
                neighbors = new LinkedList<>();
            }

            while ((value = in.nextInt()) != 0) {
                neighbors.add(value);
                otherNeighbors = graph.get(value);
                if (otherNeighbors == null) {
                    otherNeighbors = new LinkedList<>();
                }
                otherNeighbors.add(i);
                graph.put(i, neighbors);
                graph.put(value, otherNeighbors);
            }
        }
    }

    private boolean dfs(int vertex, int color) {
        colors[vertex] = color & 1;
        visited.add(vertex);
        
        List<Integer> neighbors = graph.get(vertex);
        if (neighbors != null) {
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, color + 1);
                } else if (colors[neighbor] == colors[vertex]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void printResult(PrintWriter out) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i<= n; i++) {
            builder.append(colors[i]);
        }
        out.println(builder.toString());
        out.flush();
    }
}
