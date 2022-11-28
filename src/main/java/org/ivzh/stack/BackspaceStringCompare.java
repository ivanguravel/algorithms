package org.ivzh.stack;

// https://leetcode.com/problems/backspace-string-compare/
public class BackspaceStringCompare {
  
  public boolean backspaceCompare(String s, String t) {
        Stack<Character> stack1 = removeBackSpaces(s);
        Stack<Character> stack2 = removeBackSpaces(t);
        return getStringFromStack(stack1).equals(getStringFromStack(stack2));
        
    }
   
    private Stack<Character> removeBackSpaces(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != '#') {
                 
                stack.push(c);
            } else {
                  if (!stack.isEmpty()) {
                      stack.pop(); 
                  }
                
            }
        } 
        return stack;
    }
    
    private String getStringFromStack(Stack<Character> s) {
        StringBuilder sb = new StringBuilder();
        
        while (!s.isEmpty()) {
            sb.append(s.pop());
        }
        
        return sb.toString();
    }
}
