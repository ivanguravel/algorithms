package org.ivzh.sort;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class Customs {

    int n;
    int k;
    static long mxw;
    static long mxp;
    static long a;
    static int b;
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

        System.out.printf("%.12f%n", info.stream().map(i -> i.calculatedTax).reduce(Double::sum).get());
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
        Customs.mxw = nextLong();
        Customs.mxp = nextLong();

        Customs.a = nextLong();


        Customs.b = nextInt();

        this.holder = new ArrayList<>(n+1);

        for (int i = 0; i < n ; i++) {
            holder.add(new Pair(nextLong(), nextLong()));
        }
    }


    private void provisionData() {
        info = new ArrayList<>(n+1);

        int c = 0;
        for (Pair pair : holder) {
            info.add(new CustomInformationHolder(c++, pair.weight, pair.price));
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

//    public static String round(double value, int places) {
//        System.out.printf();
//        DecimalFormat df = new DecimalFormat("#.#########");
//        df.setRoundingMode(RoundingMode.HALF_DOWN);
//        return df.format(value);
//    }

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

        private boolean isWeightBetter;

        public CustomInformationHolder() {

        }

        public CustomInformationHolder(int number, long weight, long price) {
            this.number = number;

            this.weight = weight;
            this.price = price;

            calculateTaxBeforeHack();

            this.calculatedTax = taxBeforeHack;
        }

        private static CustomInformationHolder copyAndPriceAfterHack(CustomInformationHolder holder) {
            CustomInformationHolder result = new CustomInformationHolder();
            result.number = holder.number;
            result.weight = holder.weight;
            result.price = holder.price;

            result.calculatedTax = holder.taxBeforeHack;
            result.calculateTaxAfterHack();
            return result;
        }

        private double calculateTax(long weight, long price) {
            double tax = 0;

            if (weight > Customs.mxw) {
                long overweight =  weight - Customs.mxw;
                tax = tax + (overweight * Customs.a);
            }

            if (price > Customs.mxp) {
                long overprice = price - Customs.mxp;
                double d = Customs.b / 100.0;
                tax = (tax + (overprice * d));
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

                double hackedTaxWithWeight = calculateTax(hackedWeight, price);
                double hackedTaxWithPrice = calculateTax(weight, hackedPrice);

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

            if (s.length() != 1) {
                for (int i = 0; i < chars.length; ++i) {
                    if (chars[i] != MAX_OCTET_NUMBER) {
                        return maxOctetFromCharArray(s, i);
                    }
                }
            } else {
                if (chars[0] != MAX_OCTET_NUMBER) {
                    return maxOctetFromCharArray(s, 0);
                }
            }

            return n;

        }

        private static long maxOctetFromCharArray(String s, int i) {
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
