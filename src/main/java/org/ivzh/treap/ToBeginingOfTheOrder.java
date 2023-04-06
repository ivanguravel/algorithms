package org.ivzh.treap;//package treap;

import java.io.*;
import java.util.Objects;
import java.util.Random;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;


// https://informatics.msk.ru/mod/statements/view.php?id=1974&chapterid=2791
public class ToBeginingOfTheOrder {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new ToBeginingOfTheOrder().run();
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
        TreapOperator  operator = new TreapOperator();
        for (int i = 1; i <= n; i++) {
            operator.add(i);
        }

        Treap treap = operator.root;

        for (int i = 0; i < m; i++) {
            Integer l = nextInt() -1;
            Integer r = nextInt();
            Treap[] one = operator.split(treap, r);
            Treap[] two = operator.split(one[0], l);
            treap = operator.merge(operator.merge(two[1], two[0]), one[1]);
        }
        print(treap);
    }

    private void print(Treap treap) {
        if (treap != null) {
            print(treap.left);
            writer.print(treap.x + " ");
            print(treap.right);
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

    static class TreapOperator {
        // root of the tree
        Treap root = null;

        public void add(Integer val) {
            root = merge(root, new Treap(val));
        }

        private void update(Treap treap) {
            if (treap != null) {
                treap.size = getSize(treap.left) + getSize(treap.right) + 1;
                treap.min = Math.min(treap.x, Math.min(safeGetMin(treap.left), safeGetMin(treap.right)));
            }
        }

        private int safeGetMin(Treap treap) {
            return treap != null ? treap.min : Integer.MAX_VALUE;
        }

        private int getSize(Treap treap) {
            return Objects.isNull(treap) ? 0 : treap.size;
        }

        private Treap merge(Treap t1, Treap t2) {
            if (t1 == null) {
                return t2;
            } else if (t2 == null) {
                return t1;
            }

            Treap updatedRoot;
            if (t1.y > t2.y) {
                t1.right = merge(t1.right, t2);
                updatedRoot = t1;
            } else {
                t2.left = merge(t1, t2.left);
                updatedRoot = t2;
            }
            update(updatedRoot);
            return updatedRoot;
        }

        private Treap[] split(Treap n, Integer key) {
            Treap[] result = {null, null};
            if (n == null) {
                return result;
            }

            int calculatedKey = getSize(n.left) + 1;
            if (calculatedKey > key) {
                result = split(n.left, key);
                n.left = result[1];
                result[1] = n;
                update(result[0]);
                update(result[1]);
                return result;
            } else {
                result = split(n.right, key - getSize(n.left) - 1);
                n.right = result[0];
                result[0] = n;
                update(result[0]);
                update(result[1]);
                return result;
            }
        }
    }

    static class Treap {
        Random random = new Random();


        Integer size;
        Integer x;
        Integer y;
        Integer min;
        Treap left, right;

        Treap(int x) {
            this.x = x;
            this.size = 1;
            y = random.nextInt();
        }
    }
}
