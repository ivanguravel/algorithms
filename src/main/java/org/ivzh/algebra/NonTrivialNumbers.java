package org.ivzh.algebra;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class NonTrivialNumbers {

    int n, m;
    TreeSet<Integer> primes;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new NonTrivialNumbers().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        readData(in);
        sieveOfEratosthenes(m);

        double globalMinimum = Double.MAX_VALUE;
        int result = 0;
        for (int i = n; i <= m; i++) {
            Integer ceiling = primes.ceiling(i);
            double localMinimum = (double) (calculateDividersSum(i) / i);
//            if (ceiling == null) {
//
//            } else {
//                localMinimum = ceiling;
//            }


            if (localMinimum <= globalMinimum) {
                globalMinimum = localMinimum;
                result = i;
            }
        }

        out.println(result);
        out.flush();
    }


    double calculateDividersSum(int k) {
        double result = 1;
        for (int i = 2; i*i <= k; i++) {
            if (k % i == 0) {
                result = result + i;
                if (i*i != k) {
                    result = result + (double)(k / i);
                }
            }
        }
        return result;
    }

    private void sieveOfEratosthenes(int n) {

        boolean prime[] = new boolean[n + 1];
        for (int i = 0; i < n; i++)
            prime[i] = true;

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p)
                    prime[i] = false;
            }
        }

        for (int i = 2; i <= n; i++) {
            if (prime[i])
                primes.add(i);
        }
    }

    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.m = in.nextInt();
        this.primes = new TreeSet<>();
    }
}
