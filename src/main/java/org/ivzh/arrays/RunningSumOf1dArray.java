package org.ivzh.arrays;

// https://leetcode.com/problems/running-sum-of-1d-array/
public class RunningSumOf1dArray {


    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        runningSum(a);
    }

    public static int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];

        Integer buffer = nums[0];
        result[0] = buffer;
        for (int i = 1; i < nums.length; i++) {
            result[i] = nums[i] + buffer;
            buffer = result[i];
        }

        return result;
    }
}
