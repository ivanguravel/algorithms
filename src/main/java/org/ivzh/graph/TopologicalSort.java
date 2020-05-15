package org.ivzh.graph;

import java.io.PrintWriter;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1280
public class TopologicalSort {

    Boolean answer = true;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new TopologicalSort().solve(in, out);

        out.flush();
    }

    void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Map<Integer, Set<Integer>> graph = readGraph(in, m);
        List<Integer> sequence = readSequence(in, n);

        List<Integer> integers = topSort(graph);
        Collections.reverse(integers);

        for (Integer i : integers)
            System.out.print(i + " ");

        if (answer) {
           // validate4ReverseOrder(graph, sequence, n);
            validateAnswer(integers, sequence);
        }

        if (answer) {
            out.println("YES");
        } else {
            out.println("NO");
        }
    }

    void validateAnswer(List<Integer> q, List<Integer> sequence) {
       // Collections.sort(sequence);
        answer = q.equals(sequence);
    }

    void validate4ReverseOrder(Map<Integer, Set<Integer>> graph, List<Integer> sequence, int n) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {

                if(isVertexesConnected(graph, sequence.get(j), sequence.get(i)) || sequence.get(j).equals(sequence.get(i))) {
                    this.answer = false;
                    return;
                }
            }
        }
    }

    List<Integer> topSort(Map<Integer, Set<Integer>> graph, List<Integer> sequence) {

        int index = 0;
        while (index < sequence.size()) {

        }
    }

//    public void topSort(int start, Map<Integer, Set<Integer>> graph, Set<Integer> visited, List<Integer> q ) {
//        Set<Integer> integers = graph.get(start);
//        if (Objects.nonNull(integers)) {
//            for (int vertex : integers) {
//                if (!visited.contains(vertex)) {
//                    visited.add(vertex);
//                    topSort(vertex, graph, visited, q);
//                }
//            }
//        }
//        q.add(start);
//    }

    boolean isVertexesConnected(Map<Integer, Set<Integer>> graph, int k, int v) {
        boolean res = graph.containsKey(k);
        if (res) {
            Set<Integer> s = graph.get(k);
            res = s != null && s.contains(v);
        }
        return res;
    }

    Map<Integer, Set<Integer>> readGraph(Scanner in, int count) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < count; i++) {
            int one = in.nextInt();
            int two = in.nextInt();
            if (one == two) {
                this.answer = false;
            }
            fillIn(graph, one, two);
        }
        return graph;
    }

    List<Integer> readSequence(Scanner in, int count) {
        List<Integer> result = new ArrayList<>(count + 1);
        for (int i = 0; i < count; i++) {
            result.add(in.nextInt());
        }
        return result;
    }

    void fillIn(Map<Integer, Set<Integer>> graph, Integer k, Integer v) {
        Set<Integer> val = graph.get(k);
        if (val != null) {
            val.add(v);
        } else {
            val = new HashSet<>();
            val.add(v);
            graph.put(k, val);
        }
    }
}