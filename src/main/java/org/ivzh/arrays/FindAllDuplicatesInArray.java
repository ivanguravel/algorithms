package org.ivzh.arrays;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// https://leetcode.com/problems/find-all-duplicates-in-an-array/
public class FindAllDuplicatesInArray {

    public List<Integer> findDuplicates(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new LinkedList<>();

        for (int i : nums) {
            if (map.get(i) != null) {
                result.add(i);
            } else {
                map.put(i, 1);
            }
        }
        return result;
    }
}
