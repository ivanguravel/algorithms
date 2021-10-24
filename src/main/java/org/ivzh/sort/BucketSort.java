package org.ivzh.sort;

import java.util.Arrays;
import java.util.LinkedList;

public class BucketSort {

    public static void main(String[] args) {
        int[] a = {2, 0, 7, 5,};
        int[] out = bucketSort(a);
        System.out.println(Arrays.toString(out));
    }

    public static final int[] bucketSort(int[] a) {
        int max = Arrays.stream(a).max().getAsInt();
        int bucketsSize = (int) Math.sqrt(a.length);

        LinkedList<Integer>[] buckets = new LinkedList[bucketsSize];

        for (int i = 0; i < bucketsSize; i++) {
            buckets[i] = new LinkedList<>();
        }

        for (int i : a) {
            LinkedList l = buckets[hash(i, max, bucketsSize)];
            l.add(i);
            buckets[hash(i, max, bucketsSize)] = l;
        }


        for (int i = 0; i < bucketsSize; i++) {
            buckets[i].sort(Integer::compareTo);
        }

        int[] result = new int[a.length];
        int count = 0;
        for (int i = 0; i < bucketsSize; i++) {
            LinkedList<Integer> l = buckets[i];
            if (l.isEmpty()) {
                continue;
            }
           for (Integer j : l) {
               result[count++] = j;
           }
        }

        return result;
    }

    private static int hash(int i, int max, int numberOfBuckets) {
        return (int) ((double) i / max * (numberOfBuckets - 1));
    }
}
