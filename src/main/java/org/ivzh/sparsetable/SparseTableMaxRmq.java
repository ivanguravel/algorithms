package org.ivzh.sparsetable;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://informatics.msk.ru/mod/statements/view.php?id=31776&chapterid=3309#1
public class SparseTableMaxRmq {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new SparseTableMaxRmq().run();
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
        int [] arr = new int[n];
        for (int i =0; i < n; i++) {
            arr[i] = nextInt();
        }

        SparseTable sparseTable = new SparseTable(arr);

        int k = nextInt();
        StringBuilder result = new StringBuilder();
        for (int i =0; i< k; i++) {
            int l = nextInt()-1;
            int r = nextInt()-1;
            result.append(sparseTable.max(l, r)).append(" ");

        }
        writer.println(result);
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

    static class SparseTable {
        private final int[][] sparseTable;

        public SparseTable(int[] arr) {
            int n = arr.length;
            int p = (int) log2(n);
            this.sparseTable = new int[p+1][n];

            for (int i = 0; i < n; i++) {
                sparseTable[0][i] = arr[i];
            }

            for (int i = 1; i <= p; i++) {
                for (int j = 0; (j + (1 << i) - 1) < n; j++) {
                    sparseTable[i][j] = Math.max(sparseTable[i-1][j], sparseTable[i - 1][j + (1 << (i - 1))]);
                }
            }
        }

        int max(int l, int r) {

            int j = (int) log2(r - l + 1);

            return Math.max(sparseTable[j][l], sparseTable[j][r - (1 << j) + 1]);
        }

        public static double log2(int x) {
            return Math.log(x) / Math.log(2);
        }
    }
}
