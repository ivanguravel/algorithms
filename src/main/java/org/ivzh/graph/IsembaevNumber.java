package org.ivzh.graph;


import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://acm.timus.ru/problem.aspx?space=1&num=1837
public class IsembaevNumber {

    private static final String UNDEFINED = "undefined";

    static class Node {
        String name;
        String parent;

        public Node(String name, String parent) {
            this.name = name;
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new IsembaevNumber().solve(in, out);
        out.flush();
    }

    void solve(Scanner in, PrintWriter out) {
        int n = in.nextInt();
        Map<Node, Set<Node>> graph = new HashMap<>();
        Map<String, Integer> visited = new TreeMap<>();
        for (int i = 0; i <= n; i++) {
            fillIn(graph, in.nextLine());
        }
        bfs(graph, "Isenbaev", visited);
        addUndefined(graph, visited);
        print(visited, out);
    }

    private void addUndefined(Map<Node, Set<Node>> graph, Map<String, Integer> visited) {
        for (Map.Entry<Node, Set<Node>> e : graph.entrySet()) {
            if (!visited.containsKey(e.getKey().name) && !e.getKey().name.isEmpty()) {
                visited.put(e.getKey().name, -1);
            }
        }
    }

    void fillIn(Map<Node, Set<Node>> graph, String s) {
        String[] names = s.split(" ");

        for (String name : names) {
            Node node = new Node(name, null);
            Set<Node> nodes = graph.get(node);
            Set<Node> collect = Stream
                    .of(names)
                    .filter(n -> !name.equalsIgnoreCase(n))
                    .map(n -> new Node(n , name)).collect(Collectors.toSet());
            if (nodes == null) {
                nodes = new HashSet<>();
            }
            nodes.addAll(collect);
            graph.put(node, nodes);
        }
    }

    void bfs(Map<Node, Set<Node>> graph, String start, Map<String, Integer> visited) {
        Node node = getNode(graph,start);

        if (node == null) {
            return;
        }

        Deque<Node> q = new ArrayDeque<>(graph.get(node));
        visited.put(start, 0);
        while (!q.isEmpty()) {
            node = q.removeFirst();
            if (!visited.containsKey(node.name)) {
                visited.put(node.name, visited.get(node.parent) + 1);
                q.addAll(graph.get(node));
            }
        }
    }

    Node getNode(Map<Node, Set<Node>> graph,String name) {
        return graph.keySet().stream().filter(n -> n.name.equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    void print(Map<String, Integer> visited, PrintWriter out) {
        String val;
        for (Map.Entry<String, Integer> e : visited.entrySet()) {
            val = e.getValue() == -1 ? UNDEFINED : Integer.toString(e.getValue());
            out.println(e.getKey() + " " + val);
        }
    }
}
