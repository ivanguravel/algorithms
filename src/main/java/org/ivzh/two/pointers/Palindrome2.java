package org.ivzh.two.pointers;

// https://leetcode.com/problems/valid-palindrome-ii/
public class Palindrome2 {

    public boolean validPalindrome(String original) {
        int startIndex = 0;
        int endIndex = original.length() - 1;
        int numberOfMistakes = 0;
        while (startIndex < endIndex) {
            if (original.charAt(endIndex) == original.charAt(startIndex)) {
                startIndex++;
                endIndex--;
            } else if (numberOfMistakes > 1) {
                return false;
            } else {
                if (original.charAt(startIndex+1) == original.charAt(endIndex)) {
                    startIndex++;
                }
                if (original.charAt(startIndex) == original.charAt(endIndex-1)) {
                    endIndex--;
                }
                numberOfMistakes++;
            }
        }

        return true;
    }
}
