package org.ivzh.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/find-the-duplicate-number
public class FindTheDuplicateNumber {

    public int findDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            if (map.get(i) != null) {
                return i;
            } else {
                map.put(i, 1);
            }
        }
        return -1;
    }

    public int findDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i =1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return -1;
    }


}
