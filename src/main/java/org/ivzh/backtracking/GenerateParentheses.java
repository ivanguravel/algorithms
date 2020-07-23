package org.ivzh.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class GenerateParentheses {


    public static void main(String[] args) {
        System.out.println(new GenerateParentheses().generateParenthesis(3));
    }


    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        int originalSize = n * 2;
        List<Character> characters = new ArrayList<>(originalSize + 1);
        generator(result, originalSize, characters, 0);
        return result;
    }

    private void generator(List<String> result, int originalSize, List<Character> characters, int index) {
        if (isSequenceValid(characters)) {
            result.add(getStringRepresentation(characters));
        }

        if (index <= originalSize) {
            characters.add('(');
            generator(result, originalSize, characters, index + 1);
            characters.add(')');
            generator(result, originalSize, characters, index + 1);
        }
    }

    private boolean isSequenceValid(List<Character> characters) {
        Stack<Character> validator = new Stack<>();
        for (char ch : characters) {
            if ('(' == ch) {
                validator.push(ch);
            } else {
                validator.pop();
            }
        }
        return validator.size() == 0;
    }

    String getStringRepresentation(List<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for (Character ch : list) {
            builder.append(ch);
        }
        return builder.toString();
    }
}
