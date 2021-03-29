package org.ivzh.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {

    public static void main(String[] args) {
        int []a = {2, 1, 6, 5, 3, 4};
        qSort(a, 0, a.length);
        System.out.println(Arrays.toString(a));
    }

    private static void qSort(int[] a, int l, int r) {
        if (r - l <= 1) {
            return;
        } else {
            int m = l;
            int pivot = a[random(l, r-1)];
            for (int i = l; i < r; i++) {
                if (a[i] < pivot) {
                    swap(a, i, m);
                    m++;
                }
            }
            qSort(a, l, m);
            qSort(a, m, r);
        }
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
