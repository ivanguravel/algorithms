package org.ivzh.segment.tree;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

// https://informatics.msk.ru/mod/statements/view.php?id=58619#1
public class SegmentTreeTasks {

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;


    public static void main(String[] args) {
        new SegmentTreeTasks().run();
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
        int[] arr = new int[nextInt()];

        for (int i =0; i < arr.length; i++) {
            arr[i] = nextInt();
        }

        SegmentTree tree = new SegmentTree(arr);
        int count = nextInt();
        for (int i =0; i < count; i++) {
            int j = nextInt()-1;
            int k = nextInt();
            writer.println(tree.rmqNod(j, k));
        }

//        SegmentTree tree = new SegmentTree(arr);
//        int count = nextInt();
//        StringBuilder sb = new StringBuilder();
//        for (int i =0; i < count; i++) {
//            String type = nextToken();
//            int j = nextInt()-1;
//            int k = nextInt();
//            if ("u".equalsIgnoreCase(type)) {
//                tree.set(tree.root, j, k);
//            } else {
//
//                sb.append(tree.rmqSum(j, k)).append(" ");
//            }
//        }
//        writer.println(sb);
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


    public class SegmentTree {


        private Segment root;

        public SegmentTree(int[] arr) {
            this.root = build(arr, 0, arr.length);
        }

        public Segment build(int[] arr, int l, int r) {

            Segment root = new Segment(l, r);
            if (r -l == 1) {
                root.sum = arr[l];
                root.max = arr[l];
                root.nod = arr[l];
            } else {
                int mid = l + (r - l) / 2;
                root.left = build(arr, l, mid);
                root.right = build(arr, mid, r);
                root.sum = root.left.sum + root.right.sum;
                root.max = Math.max(root.left.max, root.right.max);
                root.nod = nod(root.left.nod, root.right.nod);
            }
            return root;
        }

        public long rmqSum(int l, int r) {
            return rmqSum(this.root, l, r);
        }

        private long rmqSum(Segment root, int l, int r) {
            if ( root.start >= r || root.end <= l) {
                return 0;
            }

            if (l <= root.start && root.end <= r) {
                return root.sum;
            }

            long leftMax = rmqSum(root.left, l, r);
            long rightMax = rmqSum(root.right, l, r);
            return rightMax + leftMax;
        }

        public long rmqMax(int l, int r) {
            return rmqMax(this.root, l, r);
        }

        private long rmqMax(Segment root, int l, int r) {
            if ( root.start >= r || root.end <= l) {
                return Integer.MIN_VALUE;
            }

            if (l <= root.start && root.end <= r) {
                return root.max;
            }

            long leftMax = rmqMax(root.left, l, r);
            long rightMax = rmqMax(root.right, l, r);
            return Math.max(leftMax, rightMax);

        }

        public void set(Segment current, int position, int value) {
            if (current.start <= position && position < current.end) {

                if (current.end - current.start == 1) {
                    current.max = value;
                    current.sum = value;
                    current.nod = value;
                } else {

                    int mid = current.start + (current.end - current.start) / 2;


                    if (mid > position) {
                        set(current.left, position, value);
                    } else {
                        set(current.right, position, value);
                    }

                    current.sum = current.left.sum + current.right.sum;
                    current.max = Math.max(current.left.max, current.right.max);
                    current.nod = nod(current.left.nod, current.right.nod);
                }

            }
        }

        public long rmqNod(int l, int r) {
            return rmqNod(this.root, l, r);
        }

        private long rmqNod(Segment root, int l, int r) {
            if (root.start >= r || root.end <= l) {
                return Integer.MIN_VALUE;
            }

            if (l <= root.start && root.end <= r) {
                return root.nod;
            }

            long leftNod = rmqMax(root.left, l, r);
            long rightNod = rmqMax(root.right, l, r);
            return nod(leftNod, rightNod);
        }



        class Segment {
            public int start, end;
            public long sum;
            public long max;
            public long nod;
            public Segment left, right;

            public Segment(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }


        public long nod(long a, long b) {
            while (a != b) {
                if (a > b) {
                    a = a - b;
                } else {
                    b = b - a;
                }
            }
            return a;
        }
    }
}
