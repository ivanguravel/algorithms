package org.ivzh.sort;

import java.util.Arrays;

public class KorderStatistic {

    public static void main(String[] args) {
        int []a = {2, 1, 6, 5, 3, 4};
        int result = kOrderStatistic(a, 0, a.length, 3);
        System.out.println(result);
    }

    private static int kOrderStatistic(int[] a, int l, int r, int k) {
        if (r - l == 1) {
            return a[k];
        } else {
            int m = l;
            int pivot = a[random(l, r-1)];
            for (int i = l; i < r; i++) {
                if (a[i] < pivot) {
                    swap(a, i, m);
                    m++;
                }
            }
            if (k < m) {
                kOrderStatistic(a, l, m, k);
            } else {
                kOrderStatistic(a, m, r, k);
            }
        }
        return -1;
    }

    private static void swap(int[] a, int i, int j) {
        int k = a[i];
        a[i] = a[j];
        a[j] = k;
    }

    public static int random(int min,int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
