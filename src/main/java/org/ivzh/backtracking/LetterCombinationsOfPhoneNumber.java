package org.ivzh.backtracking;

import java.util.*;

public class LetterCombinationsOfPhoneNumber {

    private Map<Character, String> keys = new HashMap<>();

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


    public List<String> letterCombinations(String digits) {
        if ("".equals(digits)) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        backtracking(digits, 0, result, digits.length(), new LinkedList<>());
        return result;
    }

    private void backtracking(String digits, int position, List<String> result, int n, LinkedList<String> buff) {
        if (buff.size() == n) {
            String res = "";
            for (String s : new HashSet<>(buff)) {
                res = res + s;
            }
            result.add(res);
            return;
        }

        String letters = keys.get(digits.charAt(position));
        for (int i = 0; i < letters.length(); i++) {
            buff.add("" + letters.charAt(i));
            backtracking(digits, position + 1, result, n, buff);
            buff.removeLast();
        }
    }
}
