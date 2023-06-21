package org.ivzh.other;

// https://leetcode.com/problems/trapping-rain-water/description/
public class TrappingRainWater {

    public int trap(int[] height) {
        int result = 0;

        for (int i = 0; i < height.length -1; i++) {
            int left = 0, right = 0;

            for (int j = i; j >= 0; j--) {
                left = Math.max(left, height[j]);
            }

            for (int j = i; j < height.length; j++) {
                right = Math.max(right, height[j]);
            }

            result += Math.min(right, left) - height[i];
        }


        return result;
    }
}
