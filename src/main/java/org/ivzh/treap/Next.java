package org.ivzh.treap;

import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://informatics.msk.ru/mod/statements/view.php?id=1974&chapterid=2782#1
public class Next {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Next().run();
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
        Treap treap = null;
        Integer ans = null;
        for (int i = 0; i < n; i++) {
            String type = nextToken();
            int num = nextInt();

            if ("+".equals(type)) {
                if (treap == null) {
                    treap = new Treap(num);
                } else {
                    if (ans != null) {
                        num = (num + ans) %  1000000000;
                        ans = null;
                    }
                    treap= treap.add(num);
                }
            } else {
                int output = treap.next(num);
                writer.println(output);
                ans = output;
            }
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



    static class Treap {
        static private Random rand = new Random();
        public int x;
        public int y;
        public Treap left;
        public Treap right;

        public Treap(int x) {
            this(x, rand.nextInt());
        }

        public Treap(int x, int y) {
            this.x = x;
            this.y = y;
            this.left = null;
            this.right = null;
        }

        private Treap(int x, int y, Treap left, Treap right) {
            this.x = x;
            this.y = y;
            this.left = left;
            this.right = right;
        }

        public static Treap merge(Treap L, Treap R) {
            if (L == null) return R;
            if (R == null) return L;

            if (L.y > R.y) {
                return new Treap(L.x, L.y, L.left, merge(L.right, R));
            } else {
                return new Treap(R.x, R.y, merge(L, R.left), R.right);
            }
        }

        public Treap[] split(int x) {
            Treap newTree = null;
            Treap L, R;
            if (this.x < x) {
                if (right == null)
                    R = null;
                else {
                    Treap[] buffer = right.split(x);
                    newTree = buffer[0];
                    R = buffer[1];
                }
                L = new Treap(this.x, y, left, newTree);
            } else {
                if (left == null)
                    L = null;
                else {
                    Treap[] buffer = left.split(x);
                    L = buffer[0];
                    newTree = buffer[1];

                }
                R = new Treap(this.x, y, newTree, right);
            }
            return new Treap[]{L, R};
        }

        public static int findMin(Treap treap) {
            if (treap == null) {
                return -1;
            }

            if (treap.left == null) {
                return treap.x;
            }

            return findMin(treap.left);
        }

        public int next(int i) {
            Treap[] split = split(i);
            int answer = findMin(split[1]);
            merge(split[0], split[1]);
            return answer;
        }

        public boolean find(int value) {
            if (x == value)
                return true;
            if (x > value)
                if (left != null)
                    return left.find(value);
            if (x < value)
                if (right != null)
                    return right.find(value);
            return false;
        }

        public Treap add(int x) {
            if (find(x)) {
                return this;
            }
            Treap[] t = split(x);
            Treap l = t[0];
            Treap r = t[1];
            Treap m = new Treap(x, rand.nextInt());
            return merge(merge(l, m), r);
        }

    }


}
