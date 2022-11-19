package org.ivzh.strings;

// https://leetcode.com/problems/longest-palindrome/
public class LongestPalindrome {

    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        
        for (char c : s.toCharArray()) {
            map.merge(c, 1, Integer::sum);
        }
        
        int odd = 0, even =0;
        
        for (int i : map.values()) {
            if (isEven(i)) {
               even+=i;
            } else {
               even = even + (i -1) ;
               odd=1;
            }
        }
        
        return even + odd;
    }
  
    private boolean isEven(int n) {
          // another case is n ^ 1 == n +1
          return ((n & 1) != 1);
      }
}
