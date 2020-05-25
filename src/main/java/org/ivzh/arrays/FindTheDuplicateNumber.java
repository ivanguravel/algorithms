package org.ivzh.arrays;

import java.util.HashMap;
import java.util.Map;

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
}
