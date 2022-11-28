package org.ivzh.strings;

import java.util.*;

// https://leetcode.com/problems/find-all-anagrams-in-a-string/
public class FindAllAnagramsInString {
    
  
   public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> first = new HashMap<>(); 
        Map<Character, Integer> second = new HashMap<>(); 
        
        for (char c : p.toCharArray()) {
            first.merge(c, 1, Integer::sum);
        }
        
        
        int np = p.length();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            second.merge(c, 1, Integer::sum);
            
            if (i >= np) {
                c = s.charAt(i - np);
                
                if (first.getOrDefault(c, 0) == 1) {
                    first.remove(c);
                } else {
                    first.put(c, first.getOrDefault(c, 0) - 1);
                }
            }
            
            if (first.equals(second)) {
                result.add(i - np +1);
            }
        }
        
        return result;
    }
  
}
