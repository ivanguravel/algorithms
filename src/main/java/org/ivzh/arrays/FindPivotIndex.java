package org.ivzh.arrays; 


import java.util.*;

// https://leetcode.com/problems/find-pivot-index/
public class FindPivotIndex {

  public int pivotIndex(int[] nums) {
        int[] sufix = new int[nums.length];
        sufix[nums.length-1] = nums[nums.length-1];
        
        for (int i =nums.length-2; i >= 0; i--) {
            sufix[i] = sufix[i+1] + nums[i];
        }
        
        
        int current = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentSuffix = sufix[i] - nums[i];
                
            if (currentSuffix == current) {
                return i;
            }
            
            current += nums[i];
        }
        
        return -1;
  }
	
	
   public int pivotIndexAnother(int[] nums) {
        int[] sufix = new int[nums.length];
        sufix[nums.length-1] = nums[nums.length-1];
        
        for (int i =nums.length-2; i >= 0; i--) {
            sufix[i] = sufix[i+1] + nums[i];
        }
        
        int[] prefix  = new int[nums.length];
        prefix[0] = nums[0];
        
         for (int i =1; i < nums.length; i++) {
            prefix[i] = prefix[i-1] + nums[i];
         }
        
        
        for (int i = 0; i < nums.length; i++) {
            int currentSufix = sufix[i] - nums[i];
            int currentPrefix = prefix[i] - nums[i];
                
            if (currentPrefix == currentSufix) {
                return i;
            }
            
        }
        
        return -1;
    }

}
