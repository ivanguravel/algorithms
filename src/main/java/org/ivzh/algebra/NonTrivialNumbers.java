package org.ivzh.algebra;

import java.io.PrintWriter;
import java.util.*;

public class NonTrivialNumbers {

    int n, m;
    Set<Integer> primes;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new NonTrivialNumbers().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        readData(in);
        primesInRange(n, m);
        double globalMinimum = Double.MAX_VALUE;
        int result = 0;
        int previousPrime = 1;
        for (int i = n; i <= m; i++) {
            if (this.primes.isEmpty()) {
                double localMinimum = (double) (calculateDividersSum(i) / i);

                if (localMinimum <= globalMinimum) {
                    globalMinimum = localMinimum;
                    result = i;
                }
            } else {
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


    double calculateDividersSum(int k) {

        double result = 1;
        for (int i = 2; (1<<i) <= k; i++) {
            if (k % i == 0) {
                result = result + i;
                if (i*i != k) {
                    result = result + (double)(k / i);
                }

            }
        }
        return result;
    }

    void primesInRange(int a, int b) {
        for (int i = a; i <= b; i++) {
            boolean isPrimeNumber = true;

            // check to see if the number is prime
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrimeNumber = false;
                    break; // exit the inner for loop
                }
            }

            // print the number if prime
            if (isPrimeNumber) {
                primes.add(i);
            }
        }
    }

    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.m = in.nextInt();
        this.primes = new HashSet<>();
    }
}
