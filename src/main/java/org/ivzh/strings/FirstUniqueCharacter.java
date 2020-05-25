package org.ivzh.strings;

// https://leetcode.com/problems/first-unique-character-in-a-string
public class FirstUniqueCharacter {

    public int firstUniqChar(String s) {
        int result = -1;

        char ch;
        for (int i =0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (s.indexOf(ch) == s.lastIndexOf(ch)) {
                result = i;
                break;
            }
        }

        return result;
    }
}
