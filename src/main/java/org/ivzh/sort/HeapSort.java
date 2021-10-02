package org.ivzh.sort;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] a = {64,25,12,22,11};
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }


    private static void heapSort(int [] a) {
        for (int i = a.length - 1; i >= 0; i--) {
            swap(a,0, i);
            heapify(a,0, i);
        }
    }

    private static void heapify(int [] a, int root, int size) {
        int L = 2 * root + 1;
        int R = L + 1;
        int x = root; // max element from root, L, R
        if (L < size && a[x] < a[L]) {
            x = L;
        }

        if (R < size && a[x] < a[R]) {
            x = R;
        }

        if (x != root) {
            swap(a, x, root);
            heapify(a, x, size);
        }


    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
