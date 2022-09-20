package org.ivzh.arrays;


import java.util.*;


// https://leetcode.com/problems/subarray-sum-equals-k/ 
public class SubarraySumEqualsK {

  public int subarraySum(int[] nums, int k) {
        int[] sums = new int[nums.length+1];
        
        for (int i =1; i <= nums.length; i++) {
            sums[i] = sums[i-1] + nums[i-1];
        }
        
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            for (int end = start+1; end <= nums.length; end++) {
                if (sums[end] - sums[start] == k) {
                    ++count;
                }
            }
        }
        return count;
    }

}
