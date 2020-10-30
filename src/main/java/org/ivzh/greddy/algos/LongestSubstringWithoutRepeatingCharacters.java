package org.ivzh.greddy.algos;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/longest-substring-without-repeating-characters
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("pwwkew"));
    }

    // O(n) - calculations, O(n) - memory 
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        } else if (s.length() == 1) {
            return 1;
        } else {
            int longest = Integer.MIN_VALUE;
            int start = 0;
            int end = 1;
            int current = 1;
            Map<Character, Integer> map = new HashMap<>();
            map.put(s.charAt(start), 1);
            while (end < s.length()) {
                if (!map.containsKey(s.charAt(end))) {
                    map.put(s.charAt(end), 1);
                    ++end;
                    ++current;
                } else {
                    current = 1;
                    ++start;
                    end = start +1;
                    map.clear();
                    map.put(s.charAt(start), 1);
                }
                longest = Math.max(current, longest);
            }

            return longest;
        }
    }
}
