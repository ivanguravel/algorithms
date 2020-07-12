package org.ivzh.algebra;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

// https://acm.timus.ru/problem.aspx?space=1&num=1402
public class Cocktails {
    long n;
    BigDecimal nFactorial;
    Map<BigDecimal, BigDecimal> cache;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new Cocktails().solve(in, out);
    }

    void solve(Scanner in, PrintWriter out) {
        readData(in);
        BigDecimal result = new BigDecimal(0);

        for (long i = 2; i <= n; i++) {
            result = result.add(permutations(i));
        }

        out.println(result.toString());
        out.flush();
    }

    // i'm using formula for permutations from there - https://ya-znau.ru/znaniya/zn/80
    private BigDecimal permutations(long i) {
        BigDecimal additionalFactorial = factorial(n - i);
        return nFactorial.divide(additionalFactorial);
    }

    private BigDecimal factorial(long forCalculation) {

        if (cache.containsKey(forCalculation)) {
            return cache.get(forCalculation);
        } else {
            BigDecimal result = new BigDecimal(1);
            for (long i = 2; i <= forCalculation; i++) {
                result = result.multiply(new BigDecimal(i));
                cache.put(new BigDecimal(i), result);
            }
            return result;
        }
    }

    private void readData(Scanner in) {
        this.n = in.nextInt();
        this.cache = new HashMap<>(4096);
        this.nFactorial = factorial(this.n);
        this.cache.put(new BigDecimal(n), nFactorial);
    }
}
