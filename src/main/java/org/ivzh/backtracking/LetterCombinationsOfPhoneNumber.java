package org.ivzh.backtracking;

import java.util.*;

public class LetterCombinationsOfPhoneNumber {

    private Map<Character, String> keys = new HashMap<>();
    private Set<String> set = new HashSet<>();

    public LetterCombinationsOfPhoneNumber() {
        keys.put('2', "abc");
        keys.put('3', "def");
        keys.put('4', "ghi");
        keys.put('5', "jkl");
        keys.put('6', "mno");
        keys.put('7', "pqrs");
        keys.put('8', "tuv");
        keys.put('9', "wxyz");
    }


    public static void main(String[] args) {
        new LetterCombinationsOfPhoneNumber().letterCombinations("23");
    }

    public List<String> letterCombinations(String digits) {
        return new ArrayList<>(set);
    }

    private void backtracking(String digits, int position) {
        char c = digits.charAt(position);

    }
}
