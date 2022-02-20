package org.ivzh.graph;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=48&id_problem=1009
public class Evolution {


        int n;
        int one;
        int two;

        private void solve() {
            this.n = nextInt();

            this.one = nextInt();
            this.two = nextInt();

            while (one != two ) {
                if (one > two) {
                    one = one / 2;
                } else {
                    two = two / 2;
                }
            }

            println(one);
        }

        private void initGraph(Map<Integer, Integer> graph, int size) {
            graph = new TreeMap<>();

            for (int i =1; i<= size; i++) {
                graph.put(i, 0);
            }
        }

        private BufferedReader reader;
        private StringTokenizer tokenizer;
        private PrintWriter writer;

        public static void main(String[] args) {
            new Evolution().run();
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


        private int nextInt() {
            return parseInt(nextToken());
        }

        private long nextLong() {
            return parseLong(nextToken());
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
