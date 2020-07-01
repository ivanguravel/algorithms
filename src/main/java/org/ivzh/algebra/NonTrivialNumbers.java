package org.ivzh.algebra;

import java.io.PrintWriter;
import java.util.Scanner;

public class NonTrivialNumbers {

    int n, m;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new NonTrivialNumbers().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        readData(in);

        double globalMinimum = Double.MAX_VALUE;
        int result = 0;
        for (int i = n; i <= m; i++) {
            double localMinimum = (double) (calculateDividersSum(i) / i);

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

    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.m = in.nextInt();
    }
}
