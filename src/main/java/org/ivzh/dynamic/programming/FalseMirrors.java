package org.ivzh.dynamic.programming;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://acm.timus.ru/problem.aspx?space=1&num=1152
public class FalseMirrors {

    int n;
    List<Integer> balcons;
    int[] memo = new int[(1<<20)];
    int[] damage = new int[(1<<20)];

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
        int ret = memo[shot];

        if(ret == Integer.MIN_VALUE){
            for(int i = 0;i<n;++i){
                boolean valid = false;

                for(int j = 0;j < 3;++j) {
                    if ((shot & (1 << ((i + j) % n))) == 0) {
                        valid = true;
                    }
                }

                if(!valid) continue;

                int mask2 = shot;

                for(int j = 0; j<3; ++j) {
                    mask2 = mask2 | (1 << ((i + j) % n));
                }

                int temp = solve(mask2) + damage[mask2];

                if(ret == Integer.MIN_VALUE || temp<ret) {
                    ret = temp;
                }
            }

            if(ret == Integer.MIN_VALUE) {
                ret = 0;
            }
        }

        return ret;
    }

    void readData(Scanner in) {
        n = in.nextInt();
        in.nextLine();
        balcons = new ArrayList<>(n+1);
        String[] split = in.nextLine().split(" ");
        balcons.addAll(Stream.of(split).map(Integer::valueOf).collect(Collectors.toList()));

        Arrays.fill(memo, Integer.MIN_VALUE);
    }
}
