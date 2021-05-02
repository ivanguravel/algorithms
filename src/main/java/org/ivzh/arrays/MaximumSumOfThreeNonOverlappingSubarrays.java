package org.ivzh.arrays;

public class MaximumSumOfThreeNonOverlappingSubarrays {
    public int maxSumOfThreeSubarrays(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        for (int i1 = 0; i1 < n; i1++) {
            int sum1 = 0;
            for (int j1 = i1; j1 < n; j1++) {
                sum1 = sum1 + nums[j1];
                for (int i2 = j1 + 1; i2 < n; i2++) {
                    int sum2 = 0;
                    for (int j2 = i2; j2 < n; j2++) {
                        sum2 = sum2 + nums[j2];
                        for (int i3 = j2 + 1; i3 < n; i3++) {
                            int sum3 = 0;
                            for (int j3 = i3; j3 < n; j3++) {
                                sum3 = sum3 + nums[j3];
                                maxSum = Math.max(maxSum, sum3 + sum2 + sum1);
                            }
                        }
                    }
                }
            }
        }
        return maxSum;
    }
}
