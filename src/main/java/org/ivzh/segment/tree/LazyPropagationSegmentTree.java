package org.ivzh.segment.tree;

public class LazyPropagationSegmentTree {

    public static void main(String[] args) {
        int[] a = {3, 2, 4, 5, 1, 1, 5, 3};
        LazyPropagationSegmentTree tree = new LazyPropagationSegmentTree(a);

        System.out.println(tree.rmqSum(1, 4));
        System.out.println(tree.rmqSum(4, 6));

        tree.set(1, 4, 1);

        System.out.println(tree.rmqSum(1, 4));
    }

    private Segment root;

    public LazyPropagationSegmentTree(int[] arr) {
        this.root = build(arr, 0, arr.length);
    }

    public Segment build(int[] arr, int l, int r) {

        Segment root = new Segment(l, r);
        if (r -l == 1) {
            root.sum = arr[l];
        } else {
            int mid = l + (r - l) / 2;
            root.left = build(arr, l, mid);
            root.right = build(arr, mid, r);
            root.sum = root.left.sum + root.right.sum;
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

        propagate(root);
        long left = rmqSum(root.left, l, r);
        long right = rmqSum(root.right, l, r);
        return right + left;
    }

    public void set(int l, int r, long value) {
        set(this.root, l, r, value);
    }

    private void set(Segment root, int l, int r, long value) {
        if (root == null || (root.start >= r || root.end <= l)) {
            return;
        }

        if (l <= root.start && root.end <= r) {
            root.lazyPropagation = value;
            root.sum = root.lazyPropagation * (root.end - root.start);
            return;
        }

        propagate(root);
        set(root.left, l, r, value);
        set(root.right, l, r, value);
        root.sum = root.left.sum + root.right.sum;
    }

    private void propagate(Segment current) {
        Long lazyPropagation = current.lazyPropagation;
        if (lazyPropagation != null) {

            current.left.lazyPropagation = lazyPropagation;
            current.left.sum = lazyPropagation * (current.left.end - current.left.start);
            current.right.lazyPropagation = lazyPropagation;
            current.right.sum = lazyPropagation * (current.right.end - current.right.start);
            current.lazyPropagation = null;
        }
    }

    class Segment {
        public int start, end;
        public long sum;
        public Long lazyPropagation;
        public Segment left, right;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
            this.lazyPropagation = null;
        }
    }
}
