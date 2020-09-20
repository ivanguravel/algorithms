package org.ivzh.binary.search;

import java.io.PrintWriter;
import java.util.Scanner;


// https://acm.timus.ru/problem.aspx?space=1&num=1223
public class ChernobylEagleOnRoof {


    private int[][] dp = new int[1002][1002];
    private int[] binarySearch = new int[1002];


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new ChernobylEagleOnRoof().solve(in, out);
    }

    private void solve(Scanner in, PrintWriter out) {

        for (int i = 0; i <= 1001; i++) {
            // for dynamic work
            dp[0][i] = Integer.MAX_VALUE;
            if (i >= 1) {
                // we can break 1 egg for each floors
                dp[1][i] = i;
                // we got each egg on 1 floor
                dp[i][1] = 1;
                //dp[i][0] = 0;
            }
        }

        solve();
        result(in, out);
    }

    private void solve() {
        for (int i = 2; i <= 1001; i++) {
            binarySearch[i] = binarySearch[i / 2] + 1;
        }

        dp[1][1] = 1;

        int eggsCount = 1001;
        int floorsCount = 1001;

        for (int i = 2; i <= eggsCount; i++) {
            for (int j = 2; j <= floorsCount; j++) {
                Integer min = Integer.MAX_VALUE;
                for (int floor = 1; floor <= j; floor++) {
                    int min1 = dp[i - 1][floor - 1];
                    int min2 = dp[i][j - floor];
                    int current = (int) Math.max(min1, min2);
                    min = (int) Math.min(current, min);
                }
                dp[eggsCount][floorsCount] = min +1;
            }
        }
    }

    private void result(Scanner in, PrintWriter out) {
        while (true) {
            int eggs = in.nextInt();
            int floors = in.nextInt();
            if (eggs == 0 && floors == 0) {
               break;
            } else {


                int answer = binarySearch[floors] <= eggs ? binarySearch[floors] : dp[eggs][floors];
                out.println(answer);
                out.flush();
            }
        }
    }
}
