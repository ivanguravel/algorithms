package org.ivzh.dynamic.programming;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://acm.timus.ru/problem.aspx?space=1&num=1152
public class FalseMirrors {

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

            // calculate over damage different shots by masks
            for(int i = 0;i<n;++i) {
                //
                if ((shot & (1 << i)) == 0) {
                    damage[shot] += balcons.get(i);
                }
            }
        }
        System.out.println(solve(0));
    }


    int solve(int shot){
        int result = cache[shot];

        if (result == Integer.MIN_VALUE) {
            for(int i = 0;i<n;++i){
                boolean valid = false;

                int shot4SmallestDamage = shot;
                // going for every 3 elements
                for(int j = 0; j < 3; ++j) {
                    // find mask which is present in array
                    if ((shot & (1 << ((i + j) % n))) == 0) {
                        // find smallest element which is suitable for us
                        shot4SmallestDamage = shot4SmallestDamage | (1 << ((i + j) % n));
                        valid = true;
                    }
                }

                if(!valid) {
                    // if our masks are not valid - skip loop iteration
                    continue;
                }

                // find smallest element 4 smallest damage + current smallest damage :)
                int smallestDamage = solve(shot4SmallestDamage) + damage[shot4SmallestDamage];

                // if overall smallest damage > current which is found - use current smallest damage
                if(result == Integer.MIN_VALUE || smallestDamage < result) {
                    result = smallestDamage;
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
        cache = new int[120_000];
        damage = new int[120_000];
        Arrays.fill(cache, Integer.MIN_VALUE);
    }
}
