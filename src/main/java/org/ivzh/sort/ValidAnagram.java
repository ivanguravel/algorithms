package org.ivzh.sort;

import java.util.*;

// https://leetcode.com/problems/valid-anagram/
public class ValidAnagram {
  
  public boolean isAnagram(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        return String.valueOf(ch1).equalsIgnoreCase(String.valueOf(ch2));
    }
}
