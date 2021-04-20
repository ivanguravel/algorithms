package org.ivzh.binary.search;

// https://algocademy.com/app/#problem/searching/lang/java/solution/2-1
public class BinarySearch {


    public int stupid(int[] nums, int value) {
        int start = 0;
        int end = nums.length - 1;
        int middle = -1;
        while (start <= end) {
            middle = (start + end) / 2;
            if (nums[middle] == value) {
                return middle;
            } else if (nums[middle] > value) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }
}
