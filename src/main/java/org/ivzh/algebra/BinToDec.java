package org.ivzh.algebra;


public class BinToDec {


    public static void main(String[] args) {
        System.out.println(bin2dec("1101"));
        System.out.println(dec2bin(11));
    }


    private static int bin2dec(String value) {
        String forWork = new StringBuilder(value).reverse().toString();
        int result = 0;
        int n = (forWork.length() -1);
        for (int i = n; i >= 0; i--) {
            if (forWork.charAt(i) == '1') {
                result += Math.pow(2, i);
            }
        }
        return result;
    }

    private static String dec2bin(int value) {
        StringBuilder result = new StringBuilder();
        Integer pow;
        while (value > 0) {
            result.append(value % 2 == 0 ? '0' : '1');
            value = value / 2;
        }
        return result.reverse().toString();
    }
}
