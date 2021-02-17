package org.ivzh.graph;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://timus.online/problem.aspx?space=1&num=1315
public class MDPARandMIIAR {

    private static final char WATTER = 'w';
    private static final char AIR = '.';
    private static final char WALL = '#';


    private static Pair[] PAIRS = {new Pair(0, 1), new Pair(1, 0), new Pair(0, -1), new Pair(-1, 0)};

    int w;
    int h;
    int x;
    int y;
    int d;
    char[][] matrix;
    Pair[][] airAmount;
    DSU dsu;

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new MDPARandMIIAR().run();
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
        this.w = nextInt();
        this.h = nextInt();
        this.y = nextInt() - 1;
        this.x = this.h - nextInt() + 1;
        this.d = nextInt();

        this.matrix = new char[h+1][w+1];
        this.airAmount = new Pair[h+1][w+1];


        for (int j =0; j < w; j++) {
            matrix[0][j] = WATTER;
        }

        for (int i = 1; i<= h; i++) {
            String value = nextToken();
            for (int j = 0; j < w; j++) {
                matrix[i][j] = value.toCharArray()[j];
            }
        }

        bfs();
        provisionAirAmount();


            int connectedComponent = dsu.get(airAmount[this.x][this.y].x);

            for (int j =0; j < this.w; j++) {
                if (airAmount[0][j] != null) {
                    if (airAmount[0][j].x == connectedComponent) {
                        println("Can be rescued by himself");
                        flush();
                        return;
                    }
                }
            }


        println("Rescue operation required");
        flush();
    }

    private void bfs() {
        Queue<Pair> q = new LinkedList<>();
        for (int j = 0; j < w; j++) {
            q.add(new Pair(0, j));
        }
        Pair p;
        Pair it;
        while(!q.isEmpty()) {
            p = q.peek();
            q.poll();
            for (int i = 0; i < PAIRS.length -1; i++) {
                it = PAIRS[i];
                int x = p.x + it.x;
                int y = p.y + it.y;
                if (x >= 0 && x <= h && y >= 0 && y < w && matrix[x][y] == AIR) {
                    matrix[x][y] = WATTER;
                    q.add(new Pair(x, y));
                }
            }
        }
    }

    private void provisionAirAmount() {
        int count = 1;
        Queue<Pair> q = new LinkedList<>();
        for (int i = 1; i<= h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrix[i][j] == AIR) {
                    airAmount[i][j] = new Pair(count++, 0);
                    q.add(new Pair(i, j));
                }
            }
        }
        q.add(new Pair(this.x, this.y));
        airAmount[this.x][this.y] = new Pair(0, 0);
        this.dsu = new DSU(count);
        Pair temp;
        while (!q.isEmpty()) {
            temp = q.peek();
            q.poll();
            Pair p = airAmount[temp.x][temp.y];
            if (p.y >= d) {
                continue;
            }
            for (Pair it : PAIRS) {
                int x = temp.x + it.x;
                int y = temp.y + it.y;
                if (x >= 0 && x <= h && y >= 0 && y < w && matrix[x][y] != WALL && airAmount[x][y] == null) {
                    q.add(new Pair(x, y));
                    airAmount[x][y] = new Pair(p.x, p.y + 1);
                }
                if (x >= 0 && x <= h && y >= 0 && y < w && matrix[x][y] != WALL && airAmount[x][y] != null) {
                    Pair np = airAmount[x][y];
                    int currentAirAmount = p.y + np.y + 1;
                    if (currentAirAmount <= this.d) {
                        dsu.join(p.x, np.x);
                    }
                }
            }
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

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static class DSU {
        int[] parents = new int[500*500+1];
        int[] dsuSize = new int[500*500+1];

        public DSU(int n) {
            for (int i = 0; i <= n; i++) {
                dsuSize[i] = 1;
                parents[i] = i;
            }
        }

        public void join(int a, int b) {
            int parentA = get(a);
            int parentB = get(b);

            if (parentA == parentB) {
                return;
            }

            if (dsuSize[parentA] > dsuSize[parentB]) {
                swap(parentA, parentB);
            }


            dsuSize[parentB] = dsuSize[parentB] + dsuSize[parentA];

            parents[parentA] = parentB;
        }

        public int get(int vertex) {
            int parent = parents[vertex];
            if (vertex == parent) {
                return vertex;
            } else {
                return parents[vertex] = get(parent);
            }
        }

        private void swap(int a, int b) {
            int temp = a;
            a = b;
            b = temp;
        }
    }
}
