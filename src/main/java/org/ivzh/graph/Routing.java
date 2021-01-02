package org.ivzh.graph;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

// https://acm.timus.ru/problem.aspx?space=1&num=1072
public class Routing {


    int n;
    private Map<Integer, Set<Integer>> graph;
    private Map<Long, List<HostDescription>> subnets;
    private Map<Integer, Set<Long>> ip2subnet;
    int[] distance;
    int[] parentNodes;
    boolean[] visited;
    int start, end;

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Routing().run();

    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out))) {
            this.reader = reader;
            this.writer = writer;
            this.tokenizer = null;
            solve();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void solve() {
        readData();
        provisionGraph();
        bfs();
        printer();
        flush();
    }

    private void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        parentNodes[start] = -1;

        Integer parent;
        while (!q.isEmpty()) {
            parent = q.poll();
            for (Integer child : graph.get(parent)) {
                if (!visited[child]) {
                    distance[child] = distance[parent] + 1;
                    parentNodes[child] = parent;
                    visited[child] = true;
                    q.add(child);
                }
            }
        }

    }

    private void printer() {
        if (!visited[end]) {
            println("No");
        } else {
            println("Yes");
            Stack<Integer> stack = new Stack<>();
            for (int i = end; i >= start; i = parentNodes[i]) {
                stack.push(i);
            }

            while (!stack.empty()) {
                print(String.format("%d ", stack.pop()));
            }
        }
    }

    private void readData() {
        n = nextInt();
        graph = new HashMap<>();
        subnets = new HashMap<>();
        ip2subnet = new HashMap<>();
        distance =  new int[n+2];
        parentNodes =  new int[n+2];
        visited = new boolean[n+2];

        for (int i = 1; i <= n; i++) {
           int k = nextInt();
           int count = 0;
           HostDescription hostDescription;
           while (count < k) {
               hostDescription = new HostDescription(i, nextToken(), nextToken());
               List<HostDescription> pcHosts = subnets.get(hostDescription.subnet);
               if (pcHosts == null) {
                   pcHosts = new LinkedList<>();
               }
               pcHosts.add(hostDescription);
               subnets.put(hostDescription.subnet, pcHosts);
               ++count;

               //
               Set<Long> subnet = ip2subnet.get(i);
               if (subnet == null) {
                   subnet = new HashSet<>();
               }

               subnet.add(hostDescription.subnet);
               ip2subnet.put(i, subnet);
           }
        }

        start = nextInt();
        end = nextInt();
    }

    private void provisionGraph() {
        for (Integer node : ip2subnet.keySet()) {
            Set<Long> subnetsHolder = ip2subnet.get(node);
            if (subnetsHolder != null) {
                for (Long subnet : subnetsHolder) {
                    List<HostDescription> hostDescriptions = subnets.get(subnet);
                    if (hostDescriptions != null) {
                        List<Integer> integers = hostDescriptions.stream().map(hd -> hd.host).collect(Collectors.toList());
                        Set<Integer> set = graph.get(node);
                        if (set == null) {
                            set = new HashSet<>();
                        }
                        set.addAll(integers);
                        graph.put(node, set);
                        for (Integer i : integers) {
                            Set<Integer> set1 = graph.get(i);
                            if (set1 == null) {
                                set1 = new HashSet<>();
                            }
                            set1.add(node);
                            graph.put(i, set1);
                        }
                    }
                }
            }
        }
    }

    static class HostDescription {
        int host;
        long subnet;

        public HostDescription(int host, String ip, String mask) {
            this.host = host;
            long subnetConverted = toBits(ip);
            long maskConverted = toBits(mask);
            this.subnet = subnetConverted & maskConverted;
        }

        private static long toBits(String s) {
            String[] split = s.split("\\.");
            return  (Long.parseLong(split[0])<<24) +
                    (Long.parseLong(split[1])<<16) +
                    (Long.parseLong(split[2])<<8) +
                    Long.parseLong(split[3]);
        }
    }

    private int nextInt() {
        return parseInt(nextToken());
    }

    private String nextToken() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    private void println(Object o) {
        print(o);
        println();
    }

    private void print(Object o) {
        writer.print(o);
    }

    private void flush() {
        writer.flush();
    }

    private void println() {
        writer.println();
    }
}
