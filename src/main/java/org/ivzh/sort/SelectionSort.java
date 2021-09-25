package org.ivzh.sort;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] a = {64,25,12,22,11};
        selectionSort(a);

        System.out.println(Arrays.toString(a));
    }

    private static void selectionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = a[i];
            int idx = i;
            for (int j = i+1; j < a.length; j++) {
                if (min > a[j]) {
                    min = a[j];
                    idx = j;
                }
            }
            swap(a, i, idx);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
