package org.ivzh.graph;


import java.io.PrintWriter;
import java.util.*;

// https://timus.online/problem.aspx?space=1&num=1282
public class GameTree {

    private static final int END_OF_TREE = -2;

    int n;
    private int[] valuesHolder;
    private Map<Integer, List<Integer>> graph;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new GameTree().solve(in, out);
        out.flush();
    }

    private void solve(Scanner in, PrintWriter out) {
        readData(in);
        int result = dfs(1, true);
        out.println(result);
    }

    private int dfs(int i, boolean player) {
        if (valuesHolder[i] != -2) {
            return valuesHolder[i];
        } else {
            List<Integer> children = graph.get(i);
            int max = Integer.MIN_VALUE;
            if (children != null) {
                for (Integer child : children) {
                    if (player) {
                        max = Math.min(max, dfs(child,false));
                    } else {
                        max = Math.max(max, dfs(child,true));
                    }
                }
            }
            return max;
        }
    }

    private void readData(Scanner in) {
        n = in.nextInt();
        in.nextLine();
        valuesHolder = new int[1002];
        graph = new HashMap<>(1002);

        Arrays.fill(valuesHolder, END_OF_TREE);

        int count = 1;
        String line;
        String[] parts;
        Integer ancestor;
        while (count <= (n-1)) {
            line = in.nextLine();
            parts = line.split(" ");
            if ("L".equals(parts[0])) {
                valuesHolder[count] = Integer.parseInt(parts[2]);
            }
            ancestor = Integer.valueOf(parts[1]);
            List<Integer> children = graph.get(ancestor);
            if (children == null) {
                children = new LinkedList<>();
            }
            children.add(count);
            graph.put(ancestor, children);
            ++count;
        }
    }


}
