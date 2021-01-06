package org.ivzh.sort;

import java.util.*;

class FindFirstandLastPositionofElementInSortedArray {

  // O(n)
  public int[] searchRange(int[] nums, int target) {
        
        int left = -1;
        int right = -1;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                left = i;
                break;
            }
        }
        
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == target) {
                right = i;
                break;
            }
        }
        
        int[] result = new int[2];
        result[0] = left;
        result[1] = right;
        return result;
    }
}
