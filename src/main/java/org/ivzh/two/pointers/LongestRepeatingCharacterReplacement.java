package org.ivzh.two.pointers;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/longest-repeating-character-replacement/
public class LongestSubstringWithoutRepeatingCharacters {
    
    public int characterReplacement(String s, int k) {
        int start = 0;
        int[] frequencyMap = new int[26];
        int maxFrequency = 0;
        int longestSubstringLength = 0;

        for (int end = 0; end < s.length(); end += 1) {
            int currentChar = s.charAt(end) - 'A';

            frequencyMap[currentChar] += 1;

            maxFrequency = Math.max(maxFrequency, frequencyMap[currentChar]);

            boolean isValid = (end + 1 - start - maxFrequency <= k);
            if (!isValid) {
                int outgoingChar = s.charAt(start) - 'A';

                frequencyMap[outgoingChar] -= 1;

                start += 1;
            }


            longestSubstringLength = end + 1 - start;
        }

        return longestSubstringLength;
    }
  
}
