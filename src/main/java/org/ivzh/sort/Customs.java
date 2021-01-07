package org.ivzh.sort;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Customs {

    int n;
    int k;
    long mxw;
    long mxp;
    long a;
    int b;
    List<Pair> holder;
    List<CustomInformationHolder> info;
    List<CustomInformationHolder> sortedInfo;

    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new Customs().run();
    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out))) {
            this.reader = reader;
            this.writer = writer;
            this.tokenizer = null;
            solve();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void solve() {
        readData();
        provisionData();
        prepareSorting();

        for (CustomInformationHolder holder : sortedInfo) {
            if (k-- != 0) {
                holder.burnIn();
                info.set(holder.number, holder);

            } else {
                break;
            }
        }

        println(info.stream().map(i -> i.calculatedTax).reduce(Long::sum).get());
        flush();

        int i =0;
        while (n-- >= 1) {
            System.out.println(info.get(i++));
        }
        flush();
    }

    private void readData() {
        this.n = nextInt();
        this.k = nextInt();
        this.mxw = nextLong();
        this.mxp = nextLong();

        this.a = nextLong();


        this.b = nextInt();

        this.holder = new ArrayList<>(n+1);

        for (int i = 0; i < n ; i++) {
            holder.add(new Pair(nextLong(), nextLong()));
        }
    }


    private void provisionData() {
        info = new ArrayList<>(n+1);

        int c = 0;
        for (Pair pair : holder) {
            info.add(new CustomInformationHolder(c++, pair.weight, pair.price, mxw, mxp, a, b));
        }
        info.sort(Comparator.comparingInt(o -> o.number));
    }

    private void prepareSorting() {
        sortedInfo = new ArrayList<>(n+1);
        sortedInfo.addAll(info);
        sortedInfo.sort(Comparator.comparingLong(o -> o.taxAfterHack));
    }

    private long nextLong() {
        return parseLong(nextToken());
    }

    private int nextInt() {
        return parseInt(nextToken());
    }

    private String nextToken() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    private void println(Object o) {
        print(o);
        println();
    }

    private void print(Object o) {
        writer.print(o);
    }

    private void flush() {
        writer.flush();
    }

    private void println() {
        writer.println();
    }

    static class CustomInformationHolder implements Comparable<CustomInformationHolder> {

        private static final char MAX_OCTET_NUMBER = '9';

        private int number;

        private long taxBeforeHack;
        private long taxAfterHack = Integer.MIN_VALUE;

        private long calculatedTax;

        private long weight;
        private long price;

        private long hackedWeight;
        private long hackedPrice;

        private boolean isWeightBetter;

        long mxw;
        long mxp;
        long a;
        int b;

        public CustomInformationHolder(int number, long weight, long price, long mxw, long mxp, long a, int b) {
            this.mxp = mxp;
            this.mxw = mxw;
            this.a = a;
            this.b = b;

            this.weight = weight;
            this.price = price;

            calculateTaxBeforeHack();
            calculateTaxAfterHack();

            this.calculatedTax = taxBeforeHack;
        }

        private long calculateTax(long weight, long price) {
            long tax = 0;

            if (weight > mxw) {
               long overweight =  weight - mxw;
               tax = tax + (overweight * a);
            }

            if (price > mxp) {
                long overprice = price - mxp;
                double d = a / 100.0;
                tax = (long) (tax + (overprice - mxp) * d);
            }

           return tax;
        }


        private void calculateTaxBeforeHack() {
            taxBeforeHack = calculateTax(weight, price);
        }

        private void calculateTaxAfterHack() {
            long hackedWeight = changeNumberToBigger(weight);
            long hackedPrice = changeNumberToBigger(price);

            this.hackedWeight = hackedWeight;
            this.hackedPrice = hackedPrice;

            long hackedTaxWithWeight = calculateTax(hackedWeight, price);
            long hackedTaxWithPrice = calculateTax(weight, hackedPrice);

            this.isWeightBetter = hackedTaxWithWeight > hackedTaxWithPrice;

            taxAfterHack = Math.max(hackedTaxWithWeight, hackedTaxWithPrice);
        }

        private void burnIn() {
            if (isWeightBetter) {
                weight = hackedWeight;
            } else {
                price = hackedPrice;
            }
            calculatedTax = Math.max(taxAfterHack, calculatedTax);
        }

        private long changeNumberToBigger(long n) {
            String s = Long.toString(n);

            char[] chars = s.toCharArray();
            int i = 0;

            if (s.length() != 1) {
                for (i = 0; i < chars.length; i++) {
                    if (chars[i] != MAX_OCTET_NUMBER) {
                        break;
                    }
                }
            }

            StringBuilder builder = new StringBuilder(s);
            builder.setCharAt(i, MAX_OCTET_NUMBER);

            return Long.parseLong(builder.toString());

        }

        @Override
        public String toString() {
            return this.weight + " " + this.price;
        }

        @Override
        public int compareTo(CustomInformationHolder o) {
            return Long.compare(this.taxAfterHack, o.taxAfterHack);
        }
    }

    static class Pair {
        long weight;
        long price;

        public Pair(long weight, long price) {
            this.weight = weight;
            this.price = price;
        }
    }

}
