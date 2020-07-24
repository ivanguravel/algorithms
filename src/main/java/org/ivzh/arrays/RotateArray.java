package org.ivzh.arrays;


// https://leetcode.com/problems/rotate-array/
public class RotateArray {


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        new RotateArray().rotate(nums, 3);
        for (Integer i : nums) {
            System.out.print(i + " ");
        }
    }

    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int temp = nums[nums.length -1];
            int buffer = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int t = nums[i + 1];
                nums[i + 1] = buffer;
                buffer = t;
            }
            nums[i] = temp;
        }
    }
}
