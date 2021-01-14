package org.ivzh.binary.search;

import java.util.Arrays;

// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
public class FindFirstLastPositionElementSortedArray {

    public static void main(String[] args) {
        int[] t = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(new FindFirstLastPositionElementSortedArray().searchRange(t, 8)));
    }

    public int[] searchRange(int[] nums, int target) {
        int [] result = new int[2];
        if (nums.length == 0 ) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }

        result[0] = lowerBound(nums, target);
        result[1] = upperBound(nums, target);
        return result;
    }

    private int lowerBound(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int middle = (i + j) / 2;
            if (nums[middle]  >= target) {
                j = middle;
            } else  {
                i = middle + 1;
            }
        }
        return nums[i] == target ? i : -1;
    }

    private int upperBound(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int middle = (i + j + 1) / 2;
            if (target >= nums[middle]) {
                i = middle;

            } else {
                j = middle - 1;
            }
        }

        return nums[j] == target ? j : -1;
    }
}
