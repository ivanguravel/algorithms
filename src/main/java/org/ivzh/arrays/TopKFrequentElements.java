package org.ivzh.arrays;

import java.util.*;
import java.util.stream.Collectors;

public class TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int[] r = new TopKFrequentElements().topKFrequent(nums, 2);

        for (int i : r) {
            System.out.print(i + " ");
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        Integer value;
        for (Integer i : nums) {
            value = map.get(i);
            if (value != null) {
                map.put(i, value + 1);
            } else {
                map.put(i, 1);
            }
        }

        ArrayList<Map.Entry<Integer, Integer>> entries = new ArrayList<>(map.entrySet());

        entries.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));


        return entries.stream().map(e -> e.getKey()).limit(k).mapToInt(i -> i).toArray();
    }
}
