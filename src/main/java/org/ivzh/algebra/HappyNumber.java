package org.ivzh.algebra;

// https://leetcode.com/problems/happy-number/
public class HappyNumber {
  
  public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = find(n);
        }
        return n == 1;
    }
    
    private int find(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }  
  
}
