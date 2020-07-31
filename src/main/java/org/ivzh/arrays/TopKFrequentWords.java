package org.ivzh.arrays;


import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/top-k-frequent-words/
public class TopKFrequentWords {


    public static void main(String[] args) {
        System.out.println(new TopKFrequentWords().topKFrequent(Arrays.asList("i", "love", "leetcode", "i", "love", "coding"), 4));
    }


    public List<String> topKFrequent(List<String> words, int k) {
        Map<String, Integer> map = new HashMap<>();

        Integer value;
        for (String word : words) {
            value = map.get(word);
            if (value != null) {
                map.put(word, value + 1);
            } else {
                map.put(word, 1);
            }
        }

        List<Map.Entry<String, Integer>> entryList = map.entrySet().stream().sorted((o1, o2) -> {
            int compare = Integer.compare(o2.getValue(), o1.getValue());
            return compare != 0 ? compare : o1.getKey().compareTo(o2.getKey());
        }).collect(Collectors.toList());


        List<String> mostFrequent = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            mostFrequent.add(entryList.get(i).getKey());
        }

        return mostFrequent;
    }
}
