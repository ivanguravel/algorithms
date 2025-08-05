package org.ivzh.two.pointers;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/longest-repeating-character-replacement/
public class LongestSubstringWithoutRepeatingCharacters {


    // Используем скользящее окно (sliding window).
    // Но — вместо того чтобы фиксировать какой символ мы хотим получить, 
    // мы просто отслеживаем частоту самого частого символа в окне.
    // Если длина текущего окна right - left + 1
    // и мы знаем, что максимальная частота символа в окне = maxFreq,
    // то мы должны заменить windowLength - maxFreq символов, чтобы всё стало одинаковым. Если windowLength - maxFreq <= k → это валидное окно
    public int characterReplacement(String s, int k) {
        int[] freq = new int[26];
        int left = 0;
        int maxFreq = 0;
        int result = 0;
    
        for (int right = 0; right < s.length(); right++) {
            int idx = s.charAt(right) - 'A';
            freq[idx]++;
            maxFreq = Math.max(maxFreq, freq[idx]);
    
            // если нужно заменить больше чем k символов → окно невалидно
            while ((right - left + 1) - maxFreq > k) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
    
            result = Math.max(result, right - left + 1);
        }

        return result;
    }
  
}
