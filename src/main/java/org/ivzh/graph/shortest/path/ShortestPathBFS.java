package org.ivzh.graph.shortest.path;
 
import java.io.*;
import java.util.*;
 
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acmp.ru/asp/do/index.asp?main=task&id_course=2&id_section=21&id_topic=51&id_problem=649
public class ShortestPathBFS {
 
    int n;
    int[][] matrix;
    int[] distances;
    int answer;
 
    private void solve() {
        this.n = nextInt();
        this.matrix = new int[n+1][n+1];
        this.distances = new int[n+1];
        this.answer = 0;
 
        for (int i=0; i<=n;i++) {
            distances[i] = 150;
        }
 
        for (int i=1; i<=n;i++) {
            for (int j=1; j<=n;j++) {
                matrix[i][j] = nextInt();
            }
        }
 
        Integer first = nextInt();
        Integer second = nextInt();
 
        bfs(first, second);
 
        if (distances[second] < 150) {
            println(distances[second]);
        } else {
            println(-1);
        }
    }
 
    private void bfs(Integer i, Integer j) {
        distances[i] = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(i);
        while (!q.isEmpty()) {
            int k = q.poll();
            for (int z = 1; z <= n; z++) {
                if (matrix[k][z] == 1 && distances[z] > distances[k] + 1) {
                    distances[z] = distances[k] +1;
                    q.add(z);
                }
            }
        }
    }
 
//    private void solve2() {
//        this.n = nextInt();
//        this.matrix = new int[n+1][n+1];
//        this.graph = new ArrayList[n+1];
//        this.answer = 0;
//
//        for (int i=0; i<=n;i++) {
//            graph[i] = new ArrayList<>();
//        }
//
//        for (int i=1; i<=n;i++) {
//            for (int j=1; j<=n;j++) {
//                if (nextInt() == 1) {
//                    graph[i].add(j);
//                }
//            }
//        }
//
//        Integer first = nextInt();
//        Integer second = nextInt();
//
//        bfs2(first, second);
//        println(answer-1);
//    }
//
//    private void bfs2(Integer i, Integer j) {
//        Queue<Integer> q = new ArrayDeque<>();
//        q.add(i);
//        while (!q.isEmpty()) {
//            int size = q.size();
//            for (int k = 0; k < size; k++) {
//                Integer vertex = q.poll();
//                if (vertex == j) {
//                    ++answer;
//                    return;
//                }
//                q.addAll(graph[vertex]);
//            }
//            ++answer;
//        }
//    }
 
    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;
 
    public static void main(String[] args) {
        new ShortestPathBFS().run();
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
