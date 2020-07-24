package org.ivzh.arrays;


// https://leetcode.com/problems/rotate-array/
public class RotateArray {


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        new RotateArray().rotate(nums, 3);
    }

    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int temp = nums[nums.length -1];
            Integer buffer = nums[i + 1];
            Integer buffer1 = nums[i + 1];
            nums[i+1] = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                buffer = nums[i + 1];
                nums[j+1] = nums[j];
            }
        }
    }
}
