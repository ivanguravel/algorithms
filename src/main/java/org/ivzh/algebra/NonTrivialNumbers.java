package org.ivzh.algebra;

import java.io.PrintWriter;
import java.util.*;


public class NonTrivialNumbers {

    int n, m;
    int maxPrime = Integer.MIN_VALUE;
    Set<Integer> primes;

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

        if (m - n == 1) {
            out.println(n);
            out.flush();
            return;
        }

        if (m > 10_000) {
            primesInRange(m - 1000, m);
        } else {
            primesInRange(n, m);
        }

        double globalNumerator = Double.MAX_VALUE;
        int result = 0;
        int previousPrime = 0;
        for (int i = n; i <= m; i++) {
            if (m > 10000 && n <= maxPrime && maxPrime <= m) {
                result = maxPrime;
                break;
            } else if ((n < maxPrime || maxPrime > m) || this.primes.isEmpty()) {
                int localNumerator = calculateDividersSum(i);
                int localDenominator = i;

                if (localNumerator * result <= globalNumerator * localDenominator) {
                    globalNumerator = localNumerator;
                    result = localDenominator;
                }
            }
            else {
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


    int calculateDividersSum(int k) {

        int result = 1;
        for (int i = 2; i*i <= k; i++) {
            if (k % i == 0) {
                result = result + i;
                if (i * i != k) {
                    result = result + (k / i);
                }

            }
        }
        return result;
    }


    void primesInRange(int a, int b) {
        for (int i = a; i <= b; i++) {
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
