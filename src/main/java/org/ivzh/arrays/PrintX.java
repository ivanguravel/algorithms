package org.ivzh.arrays;

import java.util.Arrays;

// https://algocademy.com/app/#problem/print-x/
public class PrintX {


    public static void main(String[] args) {
        printX(9);
    }

    public static void printX(int n) {
        char[][] matrix = new char[n][n];

        for (char[] i : matrix) {
            Arrays.fill(i, '-');
        }

        int half = n / 2;
        int other = n / 2;
        int index = 0;
        for (char[] i : matrix) {
            if (index == half) {
                i[half] = 'x';
                ++index;
            } else if (index < half) {
                i[index] = 'x';
                i[i.length - 1 - index] = 'x';
                ++index;
            } else {
                ++other;
                i[other] = 'x';
                i[i.length - 1 - other] = 'x';
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println(" ");
        }
    }
}
