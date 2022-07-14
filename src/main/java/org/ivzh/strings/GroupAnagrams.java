package org.ivzh.strings;

import java.util.*;

// https://leetcode.com/problems/group-anagrams
public class GroupAnagrams {

  public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] a = s.toCharArray();
            Arrays.sort(a);
            String additional = new String(a);
            if (map.containsKey(additional)) {
                map.get(additional).add(s);
            } else {
                List<String> list = new LinkedList<>();
                list.add(s);
                map.put(additional, list);
            }
        }
        
        
        List<List<String>> result = new ArrayList<>(map.size());
        
        for (Map.Entry<String, List<String>> e : map.entrySet()) {
            result.add(e.getValue());
        }
        
        return result;
    }
}
