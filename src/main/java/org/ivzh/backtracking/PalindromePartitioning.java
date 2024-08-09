package org.ivzh.backtracking;

import java.util.*;


// https://leetcode.com/problems/palindrome-partitioning/
public class PalindromePartitioning {

    List<List<String>> list = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtrack(0, new ArrayList<>(), s);
        return list;
    }

    private void backtrack(int start, List<String> buffer, String s) {
        if (start >= s.length()) {
            list.add(new ArrayList<>(buffer));
        } 

        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                buffer.add(s.substring(start, i+1));
                backtrack(i +1, buffer, s);
                buffer.remove(buffer.size() - 1);
            }
        }
        
    }

    private boolean isPalindrome(String s, int start, int end) {

        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }

        return true;
    }

}
