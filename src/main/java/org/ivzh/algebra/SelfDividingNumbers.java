package org.ivzh.algebra;

import java.util.*;

class SelfDividingNumbers {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new LinkedList<>();
        for (int i = left; i<= right; i++) {
            if (isSelfDividing(i)) {
                list.add(i);
            }
        }
        return list;
    }
    
    private boolean isSelfDividing(int i) {
        int origin = i;
        while (i > 0) {
            int divider = i %10;
            if (divider == 0 || origin % divider != 0) {
                return  false;
            }
            i = i / 10;
        }
        return true;
    }
}
