package org.ivzh.bitmask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// https://leetcode.com/problems/letter-case-permutation/
public class LetterCasePermutation {

    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2"));
    }

    public static List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < (1<<S.length()); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j =0; j < S.length(); j++) {
                if (((1<<j) & i) == 0) {
                    sb.append(Character.toLowerCase(S.charAt(j)));
                } else {
                    sb.append(Character.toUpperCase(S.charAt(j)));
                }

            }
            result.add(sb.toString());
        }
        return result.stream().distinct().collect(Collectors.toList());
    }
}
