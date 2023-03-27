package org.ivzh.dynamic.programming;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.*;

public class LongestIncreasingSubsequence {
    private BufferedReader reader;
    private StringTokenizer tokenizer;
    private PrintWriter writer;


    public static void main(String[] args) {
        new LongestIncreasingSubsequence().run();
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
        int n = nextInt();
        int[] nums = new int[n];
        for (int i =0; i<n; i++) {
            nums[i] = nextInt();
        }

        //Collections.sort(result);
        writer.println(String
                .join(" ", lisSizeRestore(nums).stream().map(i -> Integer.toString(i)).collect(Collectors.toList())
                ));
    }

    public List<Integer> lisSizeRestore(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, MAX_VALUE);
        int[] indexes = new int[nums.length];
        int[] tracing = new int[nums.length];
        int size = MIN_VALUE;

        indexes[0] = -1;
        dp[0] = MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int index = lower(Arrays.stream(dp)
                    .boxed()
                    .collect(Collectors.toList()), nums[i]);

            if (dp[index - 1] < nums[i] && nums[i] < dp[index]) {
                dp[index] =  nums[i];
                indexes[index] = i;
                tracing[i] = indexes[index - 1];
                size = Math.max(size, index);
            }
        }

        List<Integer> result = new LinkedList<>();

        int i = indexes[size];
        while (i != -1) {
            result.add(nums[i]);
            i = tracing[i];
        }
        System.out.println(size);
        Collections.reverse(result);
        return result;
    }

    public int lisSize(int[] nums) {
        int[] dp = new int[nums.length];


        int len = 0;
        for (int i : nums) {
            int idx = lower(Arrays.stream(dp)
                    .boxed()
                    .collect(Collectors.toList()), i);
            if (idx >= 0) {
                dp[idx] = i;
                if (idx >= len) {
                    len = idx + 1;
                }
            }
        }
        return len;
    }

    public int lower(List<Integer> list, int target) {
        int start = 0;
        int end = list.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return list.get(start) >= target ? start : end;
    }

    public List<Integer>  longestCommonSubsequenceLength(List<Integer> arr) {
        List<Integer> result = new ArrayList<>();
        int[] indexes = new int[arr.size()];
        Arrays.fill(indexes, -1);
        int [] dp = new int[arr.size()];

        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (arr.get(j) > arr.get(i)) {
                    dp[i] = dp[j] + 1;
                    indexes[i] = j;
                }
            }
        }

        int max=0;
        for (int i =0; i< arr.size(); i++) {
            if (dp[i] > dp[max]) {
                max = i;
            }
        }

        while (max != -1) {
            result.add(arr.get(max));
            max = indexes[max];
        }
        Collections.reverse(result);
        return result;
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
