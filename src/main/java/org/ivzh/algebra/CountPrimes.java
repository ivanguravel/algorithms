package org.ivzh.algebra;

import java.util.Arrays;

// https://leetcode.com/problems/count-primes/
public class CountPrimes {

    public int countPrimes(int n) {
        if (n <= 0) return 0;

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        int primeCount = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                primeCount++;
                for (int j = 2 * i; j < n; j += i)
                    isPrime[j] = false;
            }
        }

        return primeCount;
    }
}
