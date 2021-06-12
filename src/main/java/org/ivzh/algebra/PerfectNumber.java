package org.ivzh.algebra;

// https://leetcode.com/problems/perfect-number/
public class PerfectNumber {

    public boolean checkPerfectNumber(int num) {
        return num == findDivisorsSum(num);
    }

    private int findDivisorsSum(int num) {
        int sum = 0;
        Integer companion;
        for (int i = 1; i*i < num; i++) {
            if (num % i == 0) {
                sum += i;
                companion = num/i;
                if (companion != num) {
                    sum += companion;
                }
            }
        }
        return sum;
    }
}
