package org.ivzh.two.pointers;


import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/minimum-window-substring/
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC");
    }

    public String minWindow(String S, String T) {

        if(T.length() > S.length()) {
            return "";
        }

        Map<Character, Integer> frequencySubstring = new HashMap<>();
        Map<Character, Integer> frequencyT = new HashMap<>();

        char[] arr = T.toCharArray();

        for (char ch : arr) {
            frequencyT.put(ch, frequencyT.getOrDefault(ch, 0) + 1);
        }

        int i =0;
        int j =0;
        int start =0;
        int end = 0;
        int min = Integer.MAX_VALUE;

        while (i < S.length()) {
            while (!fullyContains(frequencyT, frequencySubstring) && j < S.length()) {
                char c = S.charAt(j++);
                frequencySubstring.put(c, frequencySubstring.getOrDefault(c, 0) + 1);
            }


            if (fullyContains(frequencyT, frequencySubstring) && (j - i) < min) {
                min = Math.min(min, j - i);
                start = i;
                end = j;
            }
            int prevPosition = i++;
            frequencySubstring.put(S.charAt(prevPosition), frequencySubstring.get(S.charAt(prevPosition)) - 1);

        }

        return S.substring(start, end);
    }

    private boolean fullyContains(Map<Character, Integer> frequencyT, Map<Character, Integer> frequencySubstring) {
        for (Character ch : frequencyT.keySet()) {
            if (!frequencySubstring.containsKey(ch) || frequencySubstring.get(ch) < frequencyT.get(ch)) {
                return false;
            }
        }
        return true;
    }
}
