package org.ivzh.graph;

import java.io.*;
import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;


// https://informatics.msk.ru/moodle/mod/statements/view3.php?chapterid=162
public class Table {

    private static final int POSSIBLE_NODES_NEAR_ONE = 4;

    int rowSize;
    int columnSize;

    int [][] matrix;
    int [][] result;
    boolean [][] visited;

    Queue<Node> q;

    // for observing all nodes near node with 1 by x and y
    int[] byX = {0, 0, 1, -1};
    int[] byY = {-1, 1, 0, 0};

    private void solve() {
        readData();
        fillOnesToTheQueueAndMakeThoseVisited();

        // bfs
        while(!q.isEmpty()) {
            Node node = q.poll();
            Node neibor;
            for (int positionInGraph = 0; positionInGraph < POSSIBLE_NODES_NEAR_ONE; positionInGraph++) {
                neibor = new Node(node.row + byX[positionInGraph], node.column + byY[positionInGraph]);
                if (isOnBoard(neibor) && !isVisited(neibor)) {
                    visited[neibor.row][neibor.column] = true;
                    // for future observation by getting from q
                    q.add(neibor);
                    result[neibor.row][neibor.column] = result[node.row][node.column] + 1;
                }
            }
        }

        printResult();
    }

    private void readData() {
        rowSize = nextInt();
        columnSize = nextInt();

        matrix = new int[rowSize +1][columnSize +1];
        result = new int[rowSize +1][columnSize +1];
        visited = new boolean[rowSize +1][columnSize +1];

        for (int row = 0; row < rowSize; ++row) {
            for (int column = 0; column < columnSize; ++column) {
                matrix[row][column] = nextInt();
            }
        }

        q = new LinkedList<>();
    }

    // fill ones - 0(n^2)
    private void fillOnesToTheQueueAndMakeThoseVisited() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (matrix[i][j] == 1) {
                    q.add(new Node(i, j));
                    visited[i][j] = true;
                }
            }
        }
    }

    private void printResult() {
        for (int i = 0; i < rowSize; ++i) {
            for (int j = 0; j < columnSize; ++j) {
                System.out.print(String.format("%s ", Integer.toString(result[i][j])));
            }
            System.out.println();
        }
    }

    boolean isOnBoard(Node p) {
        return p.row >= 0 && p.row < rowSize && p.column >= 0 && p.column < columnSize;
    }

    boolean isVisited(Node node) {
        return visited[node.row][node.column];
    }

    public static void main(String[] args) {
        new Table().run();
    }

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

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

    private int nextInt(int radix) {
        return parseInt(nextToken(), radix);
    }

    private int nextInt() {
        return parseInt(nextToken());
    }

    private long nextLong(int radix) {
        return parseLong(nextToken(), radix);
    }

    private long nextLong() {
        return parseLong(nextToken());
    }

    private double nextDouble() {
        return parseDouble(nextToken());
    }

    private int[] nextArr(int size) {
        return stream(new int[size]).map(c -> nextInt()).toArray();
    }

    private long[] nextArrL(int size) {
        return stream(new long[size]).map(c -> nextLong()).toArray();
    }

    private double[] nextArrD(int size) {
        return stream(new double[size]).map(c -> nextDouble()).toArray();
    }

    private char[][] nextCharMatrix(int n) {
        return range(0, n).mapToObj(i -> nextToken().toCharArray()).toArray(char[][]::new);
    }

    private int[][] nextIntMatrix(final int n, final int m) {
        return range(0, n).mapToObj(i -> nextArr(m)).toArray(int[][]::new);
    }

    private double[][] nextDoubleMatrix(final int n, final int m) {
        return range(0, n).mapToObj(i -> nextArrD(m)).toArray(double[][]::new);
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

    private void printf(String format, Object... args) {
        writer.printf(format, args);
    }

    private void print(Object o) {
        writer.print(o);
    }

    private void println() {
        writer.println();
    }

    private void println(Object o) {
        print(o);
        println();
    }

    private void flush() {
        writer.flush();
    }

    static class Node {
        int row, column;
        public Node(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
