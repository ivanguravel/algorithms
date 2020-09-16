package org.ivzh.binary.search;

import java.io.PrintWriter;
import java.util.Scanner;

public class ChernobylEagleOnRoof {


    private int[][] dp = new int[1002][1002];
    private int[] binarySearch = new int[1002];


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new ChernobylEagleOnRoof().solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {
        int eggsCount = 1;
        int floorsCount = 1;

        for (int i = 2; i <= 1000; i++) {
            binarySearch[i] = binarySearch[i / 2] + 1;
        }

        dp[1][1] = 1;

        for (int i = 2; i <= 1000; i++) {

            int min1 = dp[eggsCount - 1][i - 1];
            int min2 = dp[eggsCount][floorsCount - i];

            dp[eggsCount][floorsCount] = (int) Math.min(min1, min2);
        }


        result(in, out);
    }

    private void result(Scanner in, PrintWriter out) {
        while (true) {
            int eggs = in.nextInt();
            int floors = in.nextInt();
            if (eggs == 0 && floors == 0) {
               break;
            } else {
                int answer = binarySearch[floors] <= eggs ? binarySearch[floors] : dp[eggs][floors];
                out.print(answer);
            }
        }
    }
}
