package org.ivzh.graph;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

// https://acm.timus.ru/problem.aspx?space=1&num=1205&locale=ru
public class MetroOrOnFeet {

    private final int MAX = 210;

    double speedOnFeet;
    double speedOnMetro;
    int n;
    Point[] coordinates;
    boolean[][] connections;
    double[][] graph;
    double[] time;
    int[] previous;
    boolean[] dijkstraCoveringSet;
    Point a;
    Point b;


    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;


    public static void main(String[] args) {
        new MetroOrOnFeet().run();
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
        makeDijkstra();
        prepareAndPrint();
    }

    private void readData() {
        this.speedOnFeet = nextDouble();
        this.speedOnMetro = nextDouble();

        this.n = nextInt();
        coordinates = new Point[MAX];
        connections = new boolean[MAX][MAX];
        graph = new double[MAX][MAX];
        time = new double[MAX];
        previous = new int[MAX];
        dijkstraCoveringSet = new boolean[MAX];

        Arrays.fill(coordinates, new Point(0, 0));

        int count = 0;
        while (count < n) {
            coordinates[count++] = new Point(nextInt(), nextInt());
        }

        Integer i = nextInt();
        Integer j = nextInt();

        while ((i != 0) && (j != 0)) {
            connections[i - 1][j - 1] = true;
            connections[j - 1][i - 1] = true;
            i = nextInt();
            j = nextInt();
        }

        a = new Point(nextInt(), nextInt());
        b = new Point(nextInt(), nextInt());

        coordinates[n] = a;
        coordinates[n + 1] = b;
    }

    private void provisionGraph() {
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                double speed = connections[i][j] ? speedOnMetro : speedOnFeet;
                graph[i][j] = calculatePoints(i, j, speed);
            }
        }
    }


    private double calculatePoints(int i, int j, double speed) {

        double coordinate = (coordinates[i].x - coordinates[j].x) * (coordinates[i].x - coordinates[j].x) +
                (coordinates[i].y - coordinates[j].y) * (coordinates[i].y - coordinates[j].y);

        double sqrt = Math.sqrt(coordinate);

        return sqrt / speed;
    }

    private void makeDijkstra() {
        time[n] = 0;
        int i;
        for (i = 0; i < n + 2; i++) {
            if (i != n) {
                time[i] = graph[n][i];
                previous[i] = n;
                dijkstraCoveringSet[i] = true;
            }
        }

        for (i = 0; i < n + 1; i++) {
            
            int nextStart = 0;
            
            double min = Double.MAX_VALUE;
            for (int nextStartValues = 0; nextStartValues < n + 2; nextStartValues++) {
                if (dijkstraCoveringSet[nextStartValues] && (time[nextStartValues] < min)) {
                    min = time[nextStartValues];
                    nextStart = nextStartValues;
                }
            }
            dijkstraCoveringSet[nextStart] = false;

            for (int nextStartValues = 0; nextStartValues < n + 2; nextStartValues++) {
                if (dijkstraCoveringSet[nextStartValues]) {
                    if (time[nextStart] + graph[nextStart][nextStartValues] < time[nextStartValues]) {
                        time[nextStartValues] = time[nextStart] + graph[nextStart][nextStartValues];
                        previous[nextStartValues] = nextStart;
                    }
                }
            }
        }
    }

    private void prepareAndPrint() {
        int[] restoredPath = new int[n + 1];
        int pathCounter = 0;
        int now = n + 1;
        while (now != n) {
            restoredPath[pathCounter] = now;
            now = previous[now];
            pathCounter++;
        }

        DecimalFormat df = new DecimalFormat("#.#######");
        df.setRoundingMode(RoundingMode.DOWN);
        println(round(time[n + 1], 7));
        print(String.format("%d ", pathCounter - 1));
        for (int i = pathCounter - 1; i >= 1; i--) {
            print(String.format("%d ", restoredPath[i] + 1));
        }
    }

    static class Point {
        double x;
        double y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double round(double value, int places) {
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private long nextLong() {
        return parseLong(nextToken());
    }

    private int nextInt() {
        return parseInt(nextToken());
    }

    private double nextDouble() {
        return Double.parseDouble(nextToken());
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
