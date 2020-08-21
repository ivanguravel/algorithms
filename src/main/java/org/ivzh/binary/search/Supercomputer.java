package org.ivzh.binary.search;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Supercomputer {

    BigDecimal n;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new Supercomputer().solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        readData(in);

        BigDecimal i = new BigDecimal(1);
        BigDecimal j = new BigDecimal(0).add(n);

        BigDecimal ans = new BigDecimal(-1);

        while (j.compareTo(i.add(new BigDecimal(1))) > 0) {
            BigDecimal middle  = i.add(j);
            middle = middle.divide(new BigDecimal(2)).round(new MathContext(1, RoundingMode.HALF_DOWN));


            BigDecimal forCheck = middle.multiply(middle.add(new BigDecimal(1)));
            forCheck = forCheck.divide(new BigDecimal(2)).round(new MathContext(0, RoundingMode.HALF_DOWN));

            if (forCheck.equals(this.n)) {
                ans = middle;
                break;
            } else if (forCheck.compareTo(this.n) > 0) {
                j = middle.add(new BigDecimal(0));
            } else {
                i = middle.add(new BigDecimal(0));
            }
        }

        out.print(ans.toString());
        out.flush();
    }

    private void readData(Scanner in) {
        this.n = new BigDecimal(in.next());
    }
}
