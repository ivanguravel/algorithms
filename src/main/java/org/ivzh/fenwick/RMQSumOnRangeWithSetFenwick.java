package org.ivzh.fenwick;//package fenwick;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://informatics.msk.ru/mod/statements/view.php?chapterid=3317#1
public class RMQSumOnRangeWithSetFenwick {


    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new RMQSumOnRangeWithSetFenwick().run();
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
        int[] arr = new int[n];
        for (int i =0; i <n; i++) {
            arr[i] = nextInt();
        }

        FenwickTree fenwickTree = new FenwickTree(arr);
        int m = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i =0; i <m; i++) {
            String request = nextToken();
            int l = nextInt();
            int r = nextInt();
            if ("s".equals(request)) {
                sb.append(fenwickTree.sum(l-1, r-1)).append(" ");
            } else {
                fenwickTree.set(l-1, r);
            }
        }

        writer.println(sb);
    }


    static class FenwickTree {

        int[] tree;

        public FenwickTree(int[] arr) {
            this.tree = new int[arr.length];

            for (int i = 0; i < arr.length; i++) {
                init(i, arr[i]);
            }
        }

        public void init(int i, int val) {
            for (; i < tree.length; i = (i | (i + 1))) {
                tree[i] += val;
            }
        }

        public int sum(int l, int r) {
            return sum(r) - sum(l-1);
        }

        public void set(int i, int delta) {
            init(i, delta - (sum(i) - sum(i-1)));
        }

        private int sum(int r) {
            int sum = 0;
            for (; r >= 0; r = (r & (r + 1)) - 1) {
                sum += tree[r];
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
