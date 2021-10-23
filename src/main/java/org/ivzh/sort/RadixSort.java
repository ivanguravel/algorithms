package org.ivzh.sort;

import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int[] a = {272, 720 , 310, 911 ,634, 450, 500};
        System.out.println(Arrays.toString(radixSort(a)));
    }

    private static int[] radixSort(int[] a) {
        int max = Arrays.stream(a).max().getAsInt();

        int[] result = null;
        for (int exp = 1; max / exp > 0; exp*=10) {
            result = countingSort(a, exp);
        }

        return result;
    }


    public static int[] countingSort(int[] a, int exp){

        int[] counter = new int[10];

        int[] result = new int[a.length];

        for (int i : a) {
            // 121 / 10 == 12; 12 % 10 == 2
            counter[(i / exp) % 10] += 1;
        }

        for (int i = 1; i < counter.length; i++) {
            counter[i] = counter[i] + counter[i-1];
        }

        for (int i = a.length -1; i >= 0; i--) {
            int index = counter[(a[i] / exp) % 10] - 1;
            counter[(a[i] / exp) % 10] = counter[(a[i] / exp) % 10] - 1;
            result[index] = a[i];
        }

        return result;
    }
}
