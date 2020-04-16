package org.ivzh.two.pointers;

import java.util.HashSet;
import java.util.Set;

//
public class LongestSubstringWithoutRepeatingCharacters {


    public static void main(String[] args) {

        System.out.println(solve("abcabcbb"));
        System.out.println(solve("bbbbbb"));
        System.out.println(solve("pwwkew"));
    }

    public static int solve(String s) {
            if (s.length() == 0) {
                return 0;
            }

            Set<Character> validator = new HashSet<>();
            char[] chars = s.toCharArray();
            int i = 0;
            int j = 1;
            int max = 1;
            int howLong = 0;
            validator.add(chars[i]);
            while (i < chars.length && j < chars.length) {
                if (!validator.contains(chars[j])) {
                    validator.add(chars[j++]);
                    howLong = validator.size();
                    max = Math.max(max, howLong);
                } else {
                    validator.remove(chars[i++]);
                }
            }
            return max;
        }

}
