package org.ivzh.stack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


// https://leetcode.com/problems/valid-parentheses/
public class ValidParentless {

    public boolean checkBrackets(String s) {
        Stack<Character> container = new Stack<>();
        Set<Character> openBrackets = new HashSet<>(Arrays.asList('(', '[', '{'));
        for (char c : s.toCharArray()) {
            if (openBrackets.contains(c)) {
                container.push(c);
            } else if (checkStackForReverseBracket(container, c, '(', ')')) {
                container.pop();
            } else if (checkStackForReverseBracket(container, c, '[', ']')) {
                container.pop();
            } else if (checkStackForReverseBracket(container, c, '{', '}')) {
                container.pop();
            }
        }
        return container.isEmpty();
    }

    private boolean checkStackForReverseBracket(Stack<Character> stack, char current, char directBracket, char reverseBracket) {
        return !stack.isEmpty() && current == reverseBracket && stack.peek() == directBracket;
    }
}
