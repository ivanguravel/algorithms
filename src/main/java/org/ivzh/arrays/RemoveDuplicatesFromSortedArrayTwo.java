package org.ivzh.arrays;

import java.util.*;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
// https://algocademy.com/app/#problem/remove-duplicates-from-array/
class RemoveDuplicatesFromSortedArrayTwo {
    public int removeDuplicates(int[] nums) {
        int duplicatesCount = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int j : nums){
            if(map.getOrDefault(j, 0) <= 1){
                nums[duplicatesCount++] = j;
            }
            map.put(j, map.getOrDefault(j, 0) + 1);
        }
        return duplicatesCount;
    }
    
    public int[] removeDuplicates(int[] nums) {
        Arrays.sort(nums);
        int p = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[p]) {
                nums[++p] = nums[i];
            }
        }
        return Arrays.copyOfRange(nums, 0, p+1);
    }
    
}
