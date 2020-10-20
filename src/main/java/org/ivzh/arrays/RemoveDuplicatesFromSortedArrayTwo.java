package org.ivzh.arrays;

import java.util.*;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
class RemoveDuplicatesFromSortedArrayTwo {
    public int removeDuplicates(int[] nums) {
        int duplicatesCount = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int j : nums){
            if(map.getOrDefault(j, 0) < 2){
                nums[duplicatesCount++] = j;
            }
            map.put(j, map.getOrDefault(j, 0) + 1);
        }
        return duplicatesCount;
    }
}
