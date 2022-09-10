package org.ivzh.strings;


// https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubstring {
  
  public String longestPalindrome(String s) {
        if (s == null || s.length() < 1)  { 
            return "";
        } else {
            
            int start= 0;
            int end = 0;
            for (int i = 0; i < s.length(); i++) {
                int lenForPalindromeWhenOneLetterIsDifferent = expandInTheMiddle(s, i, i);
                int len1 = expandInTheMiddle(s, i, i+1);
                int result = Math.max(len1, lenForPalindromeWhenOneLetterIsDifferent);
                if (end - start < result) {
                    start = i - (result - 1) /2;
                    end = i + result  /2;
                }
            }
            return s.substring(start, end +1);
        }
    }
    
    
    private static int expandInTheMiddle(String s, int left, int right) {
        int l = left;
        int r = right;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            --l;
            ++r;
        }
        return r - l -1;
    }
}
