package org.ivzh.algebra;

// https://leetcode.com/problems/perfect-number/
public class PerfectNumber {

    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        return num == findDivisorsSum(num);
    }

    private int findDivisorsSum(int num) {
        int sum=1;
        int companion;
        for (int i = 2; i*i < num; i++) {
            if (num % i == 0) {
                companion = num / i;
                sum = sum + i + companion;
            }
        }
        return sum;
    }
}
