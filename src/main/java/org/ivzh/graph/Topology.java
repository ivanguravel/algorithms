package org.ivzh.graph;

import java.util.*;

public class Topology {

    private static Set<Integer> visited = new HashSet<>();
    private static List<Integer> order = new LinkedList<>();

    public static void main(String[] args) {
        GraphNode graphNode = new GraphNode(1);
        GraphNode graphNode2 = new GraphNode(2);
        GraphNode graphNode3 = new GraphNode(3);
        GraphNode graphNode4 = new GraphNode(4);

        graphNode.nodes = new ArrayList<>();
        graphNode.nodes.add(graphNode2);
        graphNode.nodes.add(graphNode3);

        graphNode3.nodes = new ArrayList<>();
        graphNode3.nodes.add(graphNode4);

        topSort(graphNode);
        Collections.reverse(order);

        for (Integer i : order) {
            System.out.println(i);
        }
    }


    private static void topSort(GraphNode graphNode) {
        if (!visited.contains(graphNode.val)) {
            if (Objects.nonNull(graphNode.nodes)) {
                for (GraphNode neighbor : graphNode.nodes) {
                    topSort(neighbor);
                    visited.add(graphNode.val);
                }
            }

            order.add(graphNode.val);
        }
    }

    private static class GraphNode {
        int val;
        List<GraphNode> nodes;

        private GraphNode(int val) {
            this.val = val;
        }



    }
}
