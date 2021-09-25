package org.ivzh.sort;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {
        int[] a = {7,8,3,1};
        insertionSort(a);
        System.out.println(Arrays.toString(a));
    }

    private static void insertionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int val = a[i];
            int j = i-1;
            while (j >=0 && a[j] > val) {
                a[j +1] = a[j];
                j = j - 1;
            }
            a[j+1] = val;
        }
    }


    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
