package org.ivzh.graph;


import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

// https://acm.timus.ru/problem.aspx?space=1&num=1072
public class Routing {


    int n;
    private Map<Integer, List<HostDescription>> graph;
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
        bfs();
        printer();

    }

    private void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        boolean[] visited = new boolean[n+2];
        visited[start] = true;
        int[] d = new int[n+2];
        Integer parent;
        while (!q.isEmpty()) {
            parent = q.poll();
            for (Integer child : graph.get(parent))
        }

    }

    private void printer() {

    }

    private void readData() {
        n = nextInt();
        graph = new HashMap<>();

        for (int i = 1; i <= n; i++) {
           int k = nextInt();
           int count = 0;

           while (count < k) {
               List<HostDescription> pcHosts = graph.get(i);
               if (pcHosts == null) {
                   pcHosts = new LinkedList<>();
               }
               pcHosts.add(new HostDescription(nextToken(), nextToken()));
               graph.put(i, pcHosts);
               ++count;
           }
        }

        start = nextInt();
        end = nextInt();
    }

    static class HostDescription {
        long subnet;

        public HostDescription(String ip, String mask) {
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
