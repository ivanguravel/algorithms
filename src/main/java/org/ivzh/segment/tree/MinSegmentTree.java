package org.ivzh.segment.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// naive impl of the segment tree
public class MinSegmentTree {


    int[] arr;
    Map<Pair, Integer> map;


    public MinSegmentTree(int[] arr) {
        this.arr = arr;
        this.map = new HashMap<>();
        build(0, arr.length);
    }

    public static void main(String[] args) {
        int[] a = {2, 2, 2, 1, 5};
        MinSegmentTree segTree = new MinSegmentTree(a);
        System.out.println(segTree.getMin(2, 3));
        System.out.println(segTree.getMin(2, 5));
    }

    public void build(int l, int r) {

        if (r - l > 1) {
            int m = l + (r - l) / 2;
            Pair left = new Pair(l, m);
            Pair right = new Pair(m, r);
            build(l, m);
            build(m, r);
            map.put(new Pair(l, r), Math.min(map.get(left), map.get(right)));
        } else {
            map.put(new Pair(l, r), Math.min(arr[l], arr[l]));
        }
    }


    public int getMin(int l, int r) {
        if (r - l > 1) {
            int m = l + (r - l) / 2;
            return Math.min(getMin(l, m), getMin(m, r));
        } else {
            if (l >= 0 && r <= arr.length) {
                return map.get(new Pair(l, r));
            }
            if (l < 0 || r > arr.length) {
                return Integer.MAX_VALUE;
            }
        }
        return Integer.MAX_VALUE;
    }



    static class Pair{
        int l;
        int r;

        public Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return l == pair.l && r == pair.r;
        }

        @Override
        public int hashCode() {
            return Objects.hash(l, r);
        }
    }
}
