package org.ivzh.two.pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


// https://leetcode.com/problems/3sum
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] a, int target) {
        List<List<Integer>> result = new LinkedList<>();
        if(!(a.length < 3)) {

            Arrays.sort(a);

            List<Integer> buffer;
            Integer sum;
            for (int i = 0; i < a.length-2; i++) {

                int j = i + 1;
                int k = a.length -1;

                while (j < k) {
                    sum = a[i] + a[j] + a[k];
                    if (sum == target) {
                        buffer = new ArrayList<>();
                        buffer.add(a[i]);
                        buffer.add(a[j]);
                        buffer.add(a[k]);
                        if (!result.contains(buffer)) {
                            result.add(buffer);
                        }
                    }

                    if (sum < target) {
                        j++;
                    } else {
                        k--;
                    }
                }
            }
        }
        return result;
    }
}
