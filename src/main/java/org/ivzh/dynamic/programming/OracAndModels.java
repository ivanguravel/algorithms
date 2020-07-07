package org.ivzh.dynamic.programming;

import java.util.Scanner;

public class OracAndModels {

    static int n;
    static int[] array;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int tc = 0; tc < t; ++tc) {
            n = sc.nextInt();
            array = new int[n];
            for (int i = 0; i < n; ++i) {
                array[i] = sc.nextInt();
            }

            System.out.println(solve(array));
        }

        sc.close();
    }

    private static boolean solve(int[] s) {
        return false;
    }
}
