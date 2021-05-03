package org.ivzh.arrays;


// https://algocademy.com/app/#problem/max-sum-of-three-subarrays
public class MaximumSumOfThreeNonOverlappingSubarrays {
    
    // O(n^6)
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
    
    // O(n^3)
    public int maxSumOfThreeSubarrays(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            int sum1 = this.maxSumSubarray(Arrays.copyOfRange(nums, 0, i));
            int sum2 =0;
            for (int j = i; j < n - 1; j++) {
                sum2 = sum2 + nums[j];
                int sum3 = this.maxSumSubarray(Arrays.copyOfRange(nums, j+1, n));
                maxSum = Math.max(maxSum, sum3 + sum2 + sum1);
            }
        }
        return maxSum;
    }
    
    private int maxSumSubarray(int[] nums) {
        int result = nums[0];
        int sum = 0;
        for (int i : nums) {
            sum = i + Math.max(0, sum);
            result = Math.max(result, sum);
        }
        
        return result;
    }
}
