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
            int k = nextInt()-1;

            writer.println(tree.rmqMax(j, k));
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


    public class SegmentTree {


        private Segment root;

        public SegmentTree(int[] arr) {
            this.root = build(arr, 0, arr.length-1);
        }

        public Segment build(int[] arr, int l, int r) {
            if (l > r) {
                return null;
            }

            Segment root = new Segment(l, r);
            if (l == r) {
                root.sum = arr[l];
                root.max = arr[l];
            } else {
                int mid = l + (r - l) / 2;
                root.left = build(arr, l, mid);
                root.right = build(arr, mid+1, r);
                root.sum = root.left.sum + root.right.sum;
                root.max = Math.max(root.left.max, root.right.max);
            }
            return root;
        }

        public int rmqSum(int l, int r) {
            return rmqSum(this.root, l, r);
        }

        private int rmqSum(Segment root, int l, int r) {
            if (root.start > r || root.end < l) {
                return 0;
            }

            if (root.end == r && root.start == l) {
                return root.sum;
            }

            int mid = root.start + (root.end - root.start) / 2;

            if (mid >= r) {
                return rmqSum(root.left, l, r);
            }
            if (mid < l) {
                return rmqSum(root.right, l, r);
            }
            int leftSum = rmqSum(root.left, l, mid);
            int rightSum = rmqSum(root.right, mid+1, r);
            return leftSum + rightSum;
        }

        public int rmqMax(int l, int r) {
            return rmqMax(this.root, l, r);
        }

        private int rmqMax(Segment root, int l, int r) {
            if (root.start > r || root.end < l) {
                return Integer.MIN_VALUE;
            }

            if (root.end == r && root.start == l) {
                return root.max;
            }

            int mid = root.start + (root.end - root.start) / 2;

            if (mid >= r) {
                return rmqMax(root.left, l, r);
            }
            if (mid < l) {
                return rmqMax(root.right, l, r);
            }
            int leftMax = rmqMax(root.left, l, mid);
            int rightMax = rmqMax(root.right, mid+1, r);
            return Math.max(leftMax, rightMax);

        }

        public int rmqNod(int l, int r) {
            return rmqNod(this.root, l, r);
        }

        private int rmqNod(Segment root, int l, int r) {
            if (root.start > r || root.end < l) {
                return Integer.MIN_VALUE;
            }

            if (root.end == r && root.start == l) {
                return root.max;
            }

            int mid = root.start + (root.end - root.start) / 2;

            if (mid >= r) {
                return rmqNod(root.left, l, r);
            }
            if (mid < l) {
                return rmqNod(root.right, l, r);
            }
            int leftMax = rmqNod(root.left, l, mid);
            int rightMax = rmqNod(root.right, mid+1, r);
            return Math.max(leftMax, rightMax);

        }

        public int euler(int a, int b) {
            int test = a;
            while (test % b != 0) {
                test += a;
            }
            return test;
        }

        class Segment {
            public int start, end;
            public int sum;
            public int max;
            public int nod;
            public Segment left, right;

            public Segment(int start, int end) {
                this.start = start;
                this.end = end;
            }
        }
    }
}
