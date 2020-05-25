package org.ivzh.strings;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/sort-characters-by-frequency/
public class SortCharactersByFrequency {

    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();

        List<Character> result = new LinkedList<>();

        for (char c : arr) {
            if (map.get(c) != null) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        Map<Character,Integer> sorted =
                map.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        for (Map.Entry<Character, Integer> e : sorted.entrySet()) {
            for (int i = 0; i < e.getValue(); i++) {
                result.add(e.getKey());
            }
        }


        StringBuilder sb = new StringBuilder();

        for (Character ch : result) {
            sb.append(ch);
        }

        return sb.toString();
    }
}
