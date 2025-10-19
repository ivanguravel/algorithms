package org.ivzh.backtracking;

import java.util.*;

public class LetterCombinationsOfPhoneNumber {

   private final Map<Character, String> keys = new HashMap<>();

    public Solution() {
        keys.put('2', "abc");
        keys.put('3', "def");
        keys.put('4', "ghi");
        keys.put('5', "jkl");
        keys.put('6', "mno");
        keys.put('7', "pqrs");
        keys.put('8', "tuv");
        keys.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        backtracking(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtracking(String digits, int position, StringBuilder current, List<String> result) {
        if (position == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = keys.get(digits.charAt(position));
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtracking(digits, position + 1, current, result);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
