package org.ivzh.arrays;


// https://leetcode.com/problems/rotate-array/
public class RotateArray {


    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        new RotateArray().rotate2(nums, 3);
        for (Integer i : nums) {
            System.out.print(i + " ");
        }

        System.out.println((2 + 3) % 7) ;
    }

    public void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int temp = nums[nums.length -1];
            int buffer = nums[0];
            for (int j = 0; j < nums.length - 1; j++) {
                int tmp = nums[j + 1];
                nums[j + 1] = buffer;
                buffer = tmp;
            }
            nums[0] = temp;
        }
    }

    public void rotate2(int[] nums, int k) {
        int[] buffer = new int[nums.length];
        for (int j = 0; j < buffer.length; j++) {
            buffer[(j + k) % buffer.length] = nums[j];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = buffer[i];
        }
    }
}
