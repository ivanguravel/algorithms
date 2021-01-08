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

        int c = 0;
        for (CustomInformationHolder holder : sortedInfo) {
            if (c++ < k) {
                info.set(holder.number, holder);
            } else {
                break;
            }
        }

        println(round(info.stream().map(i -> i.calculatedTax).reduce(Double::sum).get(), 9));
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
        for (CustomInformationHolder h : info) {
            sortedInfo.add(CustomInformationHolder.copyAndPriceAfterHack(h));
        }
        sortedInfo.sort((o1, o2) -> Double.compare(o2.taxAfterHack, o1.taxAfterHack));
    }

    private long nextLong() {
        return parseLong(nextToken());
    }

    private int nextInt() {
        return parseInt(nextToken());
    }

    public static double round(double value, int places) {
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
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

    static class CustomInformationHolder {

        private static final char MAX_OCTET_NUMBER = '9';

        private int number;

        private double taxBeforeHack;
        private double taxAfterHack;

        private double calculatedTax;

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
            this.number = number;
            this.mxp = mxp;
            this.mxw = mxw;
            this.a = a;
            this.b = b;

            this.weight = weight;
            this.price = price;

            calculateTaxBeforeHack();

            this.calculatedTax = taxBeforeHack;
        }

        private static CustomInformationHolder copyAndPriceAfterHack(CustomInformationHolder holder) {
            CustomInformationHolder result = new CustomInformationHolder(holder.number, holder.weight, holder.price, holder.mxw, holder.mxp, holder.a, holder.b);
            result.calculateTaxAfterHack();
            return result;
        }

        private long calculateTax(long weight, long price) {
            long tax = 0;

            if (weight > mxw) {
                long overweight =  weight - mxw;
                tax = tax + (overweight * a);
            }

            if (price > mxp) {
                long overprice = price - mxp;
                double d = b / 100.0;
                tax = (long) (tax + (overprice * d));
            }

            return tax;
        }


        private void calculateTaxBeforeHack() {
            taxBeforeHack = calculateTax(weight, price);
            calculatedTax = taxBeforeHack;
        }

        private void calculateTaxAfterHack() {
                long hackedWeight = changeNumberToBigger(weight);
                long hackedPrice = changeNumberToBigger(price);

                this.hackedWeight = hackedWeight;
                this.hackedPrice = hackedPrice;

                long hackedTaxWithWeight = calculateTax(hackedWeight, price);
                long hackedTaxWithPrice = calculateTax(weight, hackedPrice);

                this.isWeightBetter = hackedTaxWithWeight > hackedTaxWithPrice;

                if (isWeightBetter) {
                    weight = hackedWeight;
                } else {
                    price = hackedPrice;
                }

                taxAfterHack = Math.max(hackedTaxWithWeight, hackedTaxWithPrice);
                calculatedTax = taxAfterHack;
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
