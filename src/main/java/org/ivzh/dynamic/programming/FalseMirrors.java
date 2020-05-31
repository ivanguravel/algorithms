package org.ivzh.dynamic.programming;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://acm.timus.ru/problem.aspx?space=1&num=1152
public class FalseMirrors {

    private static final int MAX = 20;

    int n;
    List<Integer> balcons;
    int[] cache;
    int[] damage;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        new FalseMirrors().solve(in, out);

        out.flush();
    }

    private void solve(Scanner in, PrintWriter out) {
        // read data
        readData(in);

        for(int shot = 0; shot < Math.pow(2, n); ++shot) {
            damage[shot] = 0;

            for(int i = 0;i<n;++i)
                if((shot & (1<<i))==0)
                    damage[shot] += balcons.get(i);
        }
        System.out.println(solve(0));
    }


    int solve(int shot){
        int result = cache[shot];

        if (result == Integer.MIN_VALUE) {
            for(int i = 0;i<n;++i){
                boolean valid = false;

                int shot2 = shot;
                for(int j = 0;j < 3;++j) {
                    if ((shot & (1 << ((i + j) % n))) == 0) {
                        shot2 = shot2 | (1 << ((i + j) % n));
                        valid = true;
                    }
                }

                if(!valid) continue;

                int temp = solve(shot2) + damage[shot2];

                if(result == Integer.MIN_VALUE || temp<result) {
                    result = temp;
                }
            }

            if (result == Integer.MIN_VALUE) {
                result = 0;
            }
        }

        return result;
    }

    void readData(Scanner in) {
        n = in.nextInt();
        in.nextLine();
        balcons = new ArrayList<>(n+1);
        String[] split = in.nextLine().split(" ");
        balcons.addAll(Stream.of(split).map(Integer::valueOf).collect(Collectors.toList()));
        cache = new int[n * 100];
        damage = new int[n * 100];
        Arrays.fill(cache, Integer.MIN_VALUE);
    }
}
