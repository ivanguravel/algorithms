package org.ivzh.fenwick;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;


// https://informatics.msk.ru/mod/statements/view.php?chapterid=111778#1
public class MobilePhones {


    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new MobilePhones().run();
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

        int first = nextInt();
        int n = nextInt();
        long[][] arr = new long[n+1][n+1];
        Fenwik2D fenwickTree = new Fenwik2D(arr);
        while (true) {
            int cmd = nextInt();

            if (cmd == 1) {
                fenwickTree.update(nextInt(), nextInt(), nextInt());
            } else if (cmd == 2) {
                writer.println(fenwickTree.sumRange(nextInt(), nextInt(),
                        nextInt() , nextInt() ));
            } else if (cmd == 3){
                return;
            }
        }
    }


    static class Fenwik2D {
        long[][] tree;

        public Fenwik2D(long[][] arr) {
            this.tree = arr;
        }

        public long sumRange(int x1, int y1, int x2, int y2) {
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

            return sum(x1, y1) - sum(x2 - 1, y1) -
                    sum(x1, y2 - 1) +
                    sum(x2 - 1, y2 - 1);
        }

        public void update(int x, int y, long delta) {
            for (int i = x; i < tree.length; i = (i | (i + 1))) {
                for (int j = y; j < tree[0].length; j = (j | (j + 1))) {
                    tree[i][j] += delta;
                }
            }
        }

        private long sum(int x, int y) {
            long sum = 0;

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

    private long nextLong() {
        return parseLong(nextToken());
    }

}
