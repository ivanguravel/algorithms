package org.ivzh.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://algocademy.com/app/#problem/smallest-k
public class SmallestKIntegers {


    public static void main(String[] args) {
        int[] n = {5, 9, 3, 6, 2, 1, 3, 2, 7, 5};
        System.out.println(Arrays.toString(kSmallestQuickSelect(n, 4)));
    }

    public int[] kSmallestNaive(int[] nums, int k) {
        Arrays.sort(nums);
        return Arrays.copyOfRange(nums, 0, k);
    }


    public int[] kSmallestHeap(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((first, second) -> {
            int compare = Integer.compare(first, second);
            return compare;
        });
        for (int i = 0; i < nums.length; i++) {
            heap.add(nums[i]);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll();
        }

        return result;
    }


    public static int[] kSmallestQuickSelect(int[] nums, int k) {
        int[] result = new int[k];
        for (int i = 1; i < k+1; i++) {
            result[i -1] = kthSmallest(nums, 0, nums.length - 1, i);
        }


        return result;
    }


    public static int partition(int[] arr, int low,
                                int high) {
        int pivot = arr[high], pivotloc = low;
        for (int i = low; i <= high; i++) {
            if (arr[i] < pivot) {
                int temp = arr[i];
                arr[i] = arr[pivotloc];
                arr[pivotloc] = temp;
                pivotloc++;
            }
        }

        int temp = arr[high];
        arr[high] = arr[pivotloc];
        arr[pivotloc] = temp;

        return pivotloc;
    }


    public static int kthSmallest(int[] arr, int low,
                                  int high, int k) {
        int partition = partition(arr, low, high);

        if (partition == k - 1)
            return arr[partition];
        else if (partition < k - 1)
            return kthSmallest(arr, partition + 1, high, k);
        else
            return kthSmallest(arr, low, partition - 1, k);
    }
}


