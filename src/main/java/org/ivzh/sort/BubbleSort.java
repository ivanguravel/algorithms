package org.ivzh.sort;

// https://algocademy.com/app/#problem/sorting/lang/java/solution/1-1/tab/solution-hints
public class BubbleSort {

    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (nums[j] > nums[i]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
