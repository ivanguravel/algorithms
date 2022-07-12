package org.ivzh.two.pointers;


// https://leetcode.com/problems/container-with-most-water/
public class ContainerWithMostWater {
  
  
  public int maxArea(int[] height) {
        int maxArea = Integer.MIN_VALUE;
        int i = 0;
        int j = height.length - 1;
        int currentHeight = 0;
        while (j > i) {
            currentHeight = Math.min(height[j] , height[i]);
            
            maxArea = Math.max(maxArea, currentHeight * (j -i));
            
            
            if (height[j] > height[i]) {
                i++;   
            } else {
                j--;
            }
        }
        
        return maxArea;
    }

}
