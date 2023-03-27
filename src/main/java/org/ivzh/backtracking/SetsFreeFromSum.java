package org.ivzh.backtracking;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Integer.parseInt;

public class SetsFreeFromSum {


    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;

    public static void main(String[] args) {
        new SetsFreeFromSum().run();
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

    private int count = 0;
    private void solve() {
        int n = nextInt();
        int[] arr = new int[n];

        for (int i =0; i < n; i++) {
            arr[i] = i;
        }
        generateAnswer(arr, n);

        writer.println(count);
    }

    private void generateAnswer(int[] nums, int n) {
        List<List<Integer>> results = new LinkedList<>();
        for (int mask =0; mask < (1<<nums.length); mask++) {
            List<Integer> current = new LinkedList<>();
            for (int i = 0; i < nums.length; i++) {
                if (((1<<i) & mask) != 0) {
                    current.add(nums[i]);
                }
            }
            for (Integer i : current) {
                if (isSetFreeFromSum(current, i, n)) {
                    ++count;
                    break;
                }
            }
        }
    }

    private boolean isSetFreeFromSum(List<Integer> nums, int target, int n) {
        Collections.sort(nums);
        int start = 0;
        int end = nums.size() -1;
        while(start < end) {
            int version = nums.get(start) + nums.get(end);

            if (version % n == target && (nums.get(start) != target) && (nums.get(end) != target)) {
                return true;
            } else if (target < version) {
                end = end - 1;
            } else {
                start = start + 1;
            }
        }
        return false;
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

    private int nextInt() {
        return parseInt(nextToken());
    }
}
