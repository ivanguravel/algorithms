package org.ivzh.greedy.algos;

// https://leetcode.com/problems/jump-game
public class JumpGame {

    public boolean canJump(int[] nums) {
        int max = nums[0];
        int maxIndex = 1;
        while (maxIndex < nums.length && max > 0) {
            max= Math.max(--max, nums[maxIndex]);
            ++maxIndex;
        }
        return maxIndex == nums.length;
    }
}
