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
            dp[1][i] = i;
            dp[i][0] = 0;
        }

        binarySearch[0] = binarySearch[1] = 1;

        solve();
        result(in, out);
    }

    private void solve() {
        for (int i = 2; i <= 1001; i++) {
            binarySearch[i] = binarySearch[i / 2] + 1;
        }

        // per tests
        int eggsCount = 1001;
        int floorsCount = 1001;

        for (int i = 2; i <= eggsCount; i++) {
            for (int j = 2; j <= floorsCount; j++) {
                Integer min = Integer.MAX_VALUE;
                for (int floor = 1; floor <= j; floor++) {
                    int min1 = binarySearch[floor-1] <= i-1 ? (int) binarySearch[floor - 1] : dp[i - 1][floor - 1];
                    int min2 = binarySearch[j-floor] <= i ? (int) binarySearch[j - floor] : dp[i][j - floor];
                    int current = (int) Math.max(min1, min2);
                    if(current < min)
                        min = current;
                    else if(current > min) {// Break early because of convexity
                        break;
                    }
                }
                dp[i][j] = min +1;
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
