package org.ivzh.fenwick;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://informatics.msk.ru/mod/statements/view.php?chapterid=3013#1
public class PointsOnPlane {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new PointsOnPlane().run();
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

        int n = nextInt();
        int m = nextInt();
        int[][] arr = new int[n][n];


        FenwickTree fenwickTree = new FenwickTree(arr);
        for (int i =0; i <m; i++) {
            String request = nextToken();
            if ("ADD".equals(request)) {
                fenwickTree.update(nextInt() - 1, nextInt() -1, 1);
            } else {
                writer.println(fenwickTree.sumRange(nextInt() -1, nextInt() -1,
                        nextInt() -1, nextInt() -1));
            }
        }
    }


    static class FenwickTree {

        int[][] tree;

        public FenwickTree(int[][] arr) {
            this.tree = arr;
        }

        public int sumRange(int x1, int y1, int x2, int y2) {
            if (x1 < x2) {
                int temp = x1;
                x1 = x2;
                x2 = temp;
            }
            if (y1 < y2) {
                int temp = y1;
                y1 = y2;
                y2 = temp;
            }


            int part1 = sum(x1, y1);

            int part2 = sum(x2 - 1, y1);

            int part3 = sum(x1, y2 - 1);

            int part4 = sum(x2 - 1, y2 - 1);
            return part1 - part2 - part3 + part4;
        }

        public void update(int x, int y, int delta) {
            for (int i = x; i < tree.length; i = (i | (i + 1))) {
                for (int j = y; j < tree[0].length; j = (j | (j + 1))) {
                    tree[i][j] += delta;
                }
            }
        }

        private int sum(int x, int y) {
            int sum = 0;

            for (int i = x; i >= 0; i = (i & (i + 1)) - 1) {
                for (int j = y; j >= 0; j = (j & (j + 1)) - 1) {
                    sum += tree[i][j];
                }
            }

            return sum;
        }
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

    private int nextInt() {
        return parseInt(nextToken());
    }
}
