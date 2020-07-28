package org.ivzh.backtracking;

import java.util.ArrayList;
import java.util.List;


// https://leetcode.com/problems/generate-parentheses/
public class GenerateParentheses {


    public static void main(String[] args) {
        System.out.println(new GenerateParentheses().generateParenthesis(3));
    }


    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generator(n, n, "", result);
        return result;
    }

    private void generator(int open, int closed, String path, List<String> result) {
        if (open <= closed) {
            if (open == 0 && closed == 0) {
                result.add(path);
            } else if (open < 0) {
                return;
            } else {
                generator(open - 1, closed, path + "(", result);
                generator(open, closed - 1, path + ")", result);
            }
        }
    }
}
