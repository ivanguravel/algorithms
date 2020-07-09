package org.ivzh.algebra;

import java.io.PrintWriter;
import java.util.*;

// https://timus.online/problem.aspx?space=1&num=1118
public class NonTrivialNumbers {

    long n, m;
    long maxPrime = Long.MIN_VALUE;
    Set<Long> primes;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new NonTrivialNumbers().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        readData(in);

        if (n == 1) {
            out.println(1);
            out.flush();
            return;
        }

        if (n == m) {
            out.println(n);
            out.flush();
            return;
        }

        double globalNumerator = Double.MAX_VALUE;
        long result = 0;
        long previousPrime = 0;
        if (m - n < 4000) {
            for (long i = n; i <= m; i++) {
                long localNumerator = calculateDividersSum(i);
                long localDenominator = i;

                if (localNumerator * result <= globalNumerator * localDenominator) {
                    globalNumerator = localNumerator;
                    result = localDenominator;
                }
            }
        } else {
            if (m > 10_000) {
                primesInRange(m - 1000, m);
            } else {
                primesInRange(n, m);
            }

            for (long i = n; i <= m; i++) {
                if (!this.primes.contains(i)) {
                    result = previousPrime;
                } else {
                    previousPrime = i;
                    result = previousPrime;
                }
            }

        }

        out.println(result);
        out.flush();
    }


    long calculateDividersSum(long k) {

        long result = 1;
        for (long i = 2; i*i <= k; i++) {
            if (k % i == 0) {
                result = result + i;
                if (i * i != k) {
                    result = result + (k / i);
                }

            }
        }
        return result;
    }


    void primesInRange(long a, long b) {
        for (long i = a; i <= b; i++) {
            boolean isPrimeNumber = true;

            // check to see if the number is prime
            for (int j = 2; j*j <= i; j++) {
                if (i % j == 0) {
                    isPrimeNumber = false;
                    break; // exit the inner for loop
                }
            }

            // print the number if prime
            if (isPrimeNumber) {
                primes.add(i);
                if (i > maxPrime) {
                    maxPrime = i;
                }
            }
        }
    }

    // 2 ... 13

    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.m = in.nextInt();
        this.primes = new HashSet<>();
    }
}
