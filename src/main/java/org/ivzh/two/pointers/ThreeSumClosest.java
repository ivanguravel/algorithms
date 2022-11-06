package org.ivzh.two.pointers;


import java.util.Arrays;

// https://leetcode.com/problems/3sum-closest/
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length-3; i++) {
            int j = i+1;
            int k = nums.length -1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int local =  Math.abs(target - sum);

                if (Math.abs(target - min) > local) {
                    min = local;
                }

                if (sum < target) {
                    ++j;
                } else {
                    --k;
                }
            }
        }

        return min;
    }
}
