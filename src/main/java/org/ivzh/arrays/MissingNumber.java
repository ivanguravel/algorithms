package org.ivzh.arrays;


import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/missing-number/
public class MissingNumber {

    public int missingNumber(int[] nums) {
        Set<Integer> container = new HashSet<>();
        for (int i : nums) {
            container.add(i);
        }

        for (int i = 0; i <= container.size(); i++) {
            if (!container.contains(i)) {
                return i;
            }
        }

        return -1;
    }
}
