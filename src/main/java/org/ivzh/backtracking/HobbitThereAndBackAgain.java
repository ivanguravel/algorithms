package org.ivzh.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HobbitThereAndBackAgain {

    int n;
    List<Integer> sequence;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        new HobbitThereAndBackAgain().solve(s);
    }

    private void solve(Scanner s) {
        this.n = s.nextInt();
        this.sequence = new ArrayList<>(n+1);
        generateSeq();
    }

    private void generateSeq() {
        for (int i = 1; i <= n; i++) {
            sequence.add(i);
        }
    }
}
