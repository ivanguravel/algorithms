package org.ivzh.arrays;


import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/contains-duplicate/
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            if (map.get(i) != null) {
                return true;
            } else {
                map.put(i, 1);
            }
        }
        return false;
    }
}
