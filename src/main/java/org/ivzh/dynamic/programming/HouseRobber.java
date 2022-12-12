package org.ivzh.dynamic.programming;

// https://leetcode.com/problems/house-robber/
public class HouseRobber {
  
  public int rob(int[] nums) {
        int[] result = new int[nums.length +1];
        result[0] = 0;
        result[1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            result[i+1] = Math.max(result[i], result[i-1] + nums[i]);
        }

        return result[nums.length];
    }
}
