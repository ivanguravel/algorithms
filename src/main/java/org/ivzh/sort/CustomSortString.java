package org.ivzh.sort;

import java.util.*;

// https://leetcode.com/problems/custom-sort-string
public class CustomSortString {
  
    public String customSortString(String alphabet, String data) {
        Map<Character, Integer> counter = new LinkedHashMap<>();

        for (char c : data.toCharArray()) {
            counter.merge(c, 1, Integer::sum);
        }

        StringBuilder result = new StringBuilder();
        for (char c : alphabet.toCharArray()) {
            int count = counter.getOrDefault(c, 0);
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    result.append(c);
                }
                counter.remove(c);
            }
        }


        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                result.append(entry.getKey());
            }
        }

        return result.toString();
    }
}
