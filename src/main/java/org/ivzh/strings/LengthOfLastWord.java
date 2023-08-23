package org.ivzh.strings;

// https://leetcode.com/problems/length-of-last-word/description/
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        String[] splitted = s.split(" ");

        return splitted[splitted.length -1].length();
    }
}
