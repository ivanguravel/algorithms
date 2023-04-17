package org.ivzh.treap;//package treap;

import java.io.*;
import java.util.Objects;
import java.util.Random;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

// https://informatics.msk.ru/mod/statements/view.php?id=1974&chapterid=2789#1
public class Swapper {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Swapper().run();
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
        Integer n, m;
        int count = 1;
        while (true) {
            n = nextInt();
            m = nextInt();

            if (n == 0 && m == 0) {
                return;
            }

            TreapOperator operator1 = new TreapOperator();
            TreapOperator operator2 = new TreapOperator();

            for (int a = 0; a < n; a++) {
                if (a % 2 == 0) {
                    operator1.add(nextInt());
                } else {
                    operator2.add(nextInt());
                }
            }

            writer.println("Swapper " + count++ + ":");
            for (int a = 0; a < m; a++) {
                Integer i = nextInt();
                Integer j = nextInt();
                Integer k = nextInt();

                if (i == 1) {
                    swap(operator1, operator2, j, k);
                } else {
                    writer.println(sum(operator1, operator2, j, k));
                }
            }


        }
    }

    private long sum(TreapOperator operator1, TreapOperator operator2, int l, int r) {
        if (l < r) {
            int l1 = (l + 1) / 2;
            int r1 = r /2;
            int l2 = l / 2;
            int r2 = (r + 1) / 2 - 1;

            Treap[] split1One = operator1.split(operator1.root, l1);
            Treap[] split1Two = operator1.split(split1One[1], r1 - l1 + 1);
            Treap resultOne = split1Two[0];

            Treap[] split2One = operator2.split(operator2.root, l2);
            Treap[] split2Two = operator2.split(split2One[1], r2 - l2 + 1);
            Treap resultTwo = split2Two[0];

            long answer = operator1.safeGetSum(resultOne) + operator2.safeGetSum(resultTwo);


            operator1.root = operator1.merge(operator1.merge(split1One[0], resultOne), split1Two[1]);
            operator2.root = operator2.merge(operator2.merge(split2One[0], resultTwo), split2Two[1]);

            return answer;

        } else {
            return 0;
        }
    }

    private void swap(TreapOperator operator1, TreapOperator operator2, int l ,int r) {
        if (l > r) {
            return;
        }

        int l1 = (l + 1) / 2;
        int r1 = r /2;
        int l2 = l / 2;
        int r2 = (r + 1) / 2 - 1;

        Treap[] split1One = operator1.split(operator1.root, l1);
        Treap[] split1Two = operator1.split(split1One[1], r1 - l1 + 1);
        Treap resultOne = split1Two[0];

        Treap[] split2One = operator2.split(operator2.root, l2);
        Treap[] split2Two = operator2.split(split2One[1], r2 - l2 + 1);
        Treap resultTwo = split2Two[0];

        operator1.root = operator1.merge(operator1.merge(split1One[0], resultTwo), split1Two[1]);
        operator2.root = operator2.merge(operator2.merge(split2One[0], resultOne), split2Two[1]);
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
        Treap root = null;

        public void add(Integer val) {
            root = merge(root, new Treap(val));
        }

        private void update(Treap treap) {
            if (treap != null) {
                treap.size = getSize(treap.left) + getSize(treap.right) + 1;
                treap.sum = treap.x + safeGetSum(treap.left) + safeGetSum(treap.right);
            }
        }

        private long safeGetSum(Treap treap) {
            return treap != null ? treap.sum : 0L;
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
        Integer x=0;
        Integer y;
        Long sum = 0L;
        Treap left, right;

        Treap(int x) {
            this.x = x;
            this.size = 1;
            y = random.nextInt();
        }
    }
}