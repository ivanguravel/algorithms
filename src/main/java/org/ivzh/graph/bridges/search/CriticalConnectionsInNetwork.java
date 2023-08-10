package org.ivzh.graph.bridges.search;

import java.util.*;

// https://leetcode.com/problems/critical-connections-in-a-network/
// https://e-maxx.ru/algo/bridge_searching
public class CriticalConnectionsInNetwork {

    private int timer = 0;
    Map<Integer, List<Integer>> graph = new HashMap<>();
    List<List<Integer>> bridges = new ArrayList<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        createGraph(connections);

        int[] discoveryTimeForeachNodes = new int[n];
        int[] timeOfVisitingNodes = new int[n];
        boolean[] visited = new boolean[n];


        dfs(0, -1, discoveryTimeForeachNodes, timeOfVisitingNodes, visited);

        return bridges;
    }

    private void dfs(int current, int parent,
                     int[] discoveryTimeForeachNodes, int[] timeOfVisitingNodes, boolean[] visited) {

        visited[current] = true;
        discoveryTimeForeachNodes[current] = timer++;
        timeOfVisitingNodes[current] = discoveryTimeForeachNodes[current];

        List<Integer> neighbors = graph.get(current);

        if (neighbors != null) {
            for (Integer vertex : neighbors) {
                if (vertex == parent) {
                    continue;
                }

                if (visited[vertex]) {
                    timeOfVisitingNodes[current] = Math.min(timeOfVisitingNodes[current], discoveryTimeForeachNodes[vertex]);
                } else {
                    dfs(vertex, current, discoveryTimeForeachNodes, timeOfVisitingNodes, visited);
                    timeOfVisitingNodes[current] = Math.min(timeOfVisitingNodes[current], timeOfVisitingNodes[vertex]);
                    if (discoveryTimeForeachNodes[current] < timeOfVisitingNodes[vertex]) {
                        bridges.add(Arrays.asList(current, vertex));
                    }
                }
            }
        }
    }

    private void createGraph(List<List<Integer>> connections) {
        for (List<Integer> conn : connections) {
            int v = conn.get(0);
            int e = conn.get(1);

            graph.putIfAbsent(v, new ArrayList<>());
            graph.putIfAbsent(e, new ArrayList<>());

            graph.get(v).add(e);
            graph.get(e).add(v);
        }
    }
}
