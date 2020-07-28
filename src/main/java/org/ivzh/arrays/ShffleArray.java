package org.ivzh.arrays;

// https://leetcode.com/problems/shuffle-the-array
public class ShffleArray {

    public int[] shuffle(int[] nums, int n) {
        int i = 0;
        int j = n;
        int k =0;
        int[] result = new int[nums.length];
        while (i < n && j < nums.length) {
            result[k++] = nums[i];
            i++;
            result[k++] = nums[j];
            j++;
        }
        return result;
    }
}
