package org.ivzh.arrays;


import java.util.*;
import java.util.stream.Collectors;


// https://leetcode.com/problems/top-k-frequent-words/
public class TopKFrequentWords {


    public static void main(String[] args) {
        List<String> strings = Arrays.asList("i", "love", "leetcode", "i", "love", "coding");
        String[] r = new String[strings.size()];
        System.out.println(new TopKFrequentWords().topKFrequent3(strings.toArray(r), 3));
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

    // will be fastest in distributed computing
    public List<String> topKFrequent2(String[] words, int k) {
        return Arrays
                .asList(words)
                .parallelStream()
                .map(String::toLowerCase)
                .collect(Collectors.toMap(w -> w, w -> 1, Integer::sum))
                .entrySet()
                .parallelStream()
                .sorted(Collections.reverseOrder((o1, o2) -> {
                    int compare = Long.compare(o1.getValue(), o2.getValue());
                    return compare != 0 ? compare : o2.getKey().compareTo(o1.getKey());
                }))
                .collect(Collectors.toList())
                .parallelStream()
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // fastest - 94%
    public List<String> topKFrequent3(String[] words, int k) {

        Map<String, Long> map = new HashMap<>();

        Long value;
        for (String word : words) {
            value = map.get(word);
            if (value != null) {
                map.put(word, value + 1);
            } else {
                map.put(word, 1L);
            }
        }

        PriorityQueue<AbstractMap.SimpleEntry<String, Long>> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            int compare = Long.compare(o2.getValue(), o1.getValue());
            return compare != 0 ? compare : o1.getKey().compareTo(o2.getKey());
        });

        for (Map.Entry<String, Long> e : map.entrySet()) {
            priorityQueue.add(new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue()));
        }

        List<String> mostFrequent = new LinkedList<>();
        int index = 0;
        while (!priorityQueue.isEmpty() && index++ < k) {
            mostFrequent.add(priorityQueue.poll().getKey());
        }

        return mostFrequent;
    }
    
    
    
    public List<String> topKFrequentTrie(String[] words, int k) {
        Trie trie = new Trie();

        for (String word : words) {
            insert(trie, word);
        }

        PriorityQueue<java.util.AbstractMap.SimpleEntry<String, Integer>> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            int compare = Integer.compare(o2.getValue(), o1.getValue());
            return compare != 0 ? compare : o1.getKey().compareTo(o2.getKey());
        });

        fillInQ(priorityQueue, trie);

        List<String> mostFrequent = new LinkedList<>();
        int index = 0;
        while (!priorityQueue.isEmpty() && index++ < k) {
            mostFrequent.add(priorityQueue.poll().getKey());
        }

        return mostFrequent;
    }
    
    
    
    void insert(Trie trie, String s) {
        Trie current = trie;
        // fill in trie
        for (char ch : s.toCharArray()) {
            current.holder.putIfAbsent(ch, new Trie());
            current = current.holder.get(ch);
        }
        current.key = s;
        ++current.count;
    }


    void fillInQ(PriorityQueue<java.util.AbstractMap.SimpleEntry<String, Integer>> priorityQueue, Trie trie) {
        if (trie != null) {
            for (Map.Entry<Character, Trie> e : trie.holder.entrySet()) {
                if (e.getValue().count != 0) {
                    priorityQueue.add(new java.util.AbstractMap.SimpleEntry<>(e.getValue().key, e.getValue().count));
                }
                fillInQ(priorityQueue, e.getValue());
            }
        }
    }


    static class Trie {
        int count = 0;
        String key = null;
        Map<Character, Trie> holder = new HashMap<>();
    }
}
}
