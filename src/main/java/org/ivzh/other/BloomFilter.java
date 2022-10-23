package org.ivzh.other;

import java.util.Random;

public class BloomFilter {


    private final boolean[] container;
    private final int[] hashes;


    public BloomFilter(int n, int m) {
        this.container = new boolean[n];
        this.hashes = new int[m];

        Random random = new Random();
        for (int i = 0; i < m; i++) {
            this.hashes[i] = random.nextInt();
        }
    }

    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter(100, 10);
        bloomFilter.add(10);
        System.out.println(bloomFilter.contains(10));
    }

    public void add(Object o) {
        for (int i=0; i<hashes.length; ++i) {
            int h = o.hashCode() & hashes[i];
            container[Math.abs(h) & container.length] = true;
        }
    }

    public boolean contains(Object o) {
        for (int i=0; i<hashes.length; ++i) {
            int h = o.hashCode() & hashes[i];
            if (!container[Math.abs(h) & container.length]) {
                return false;
            }
        }
        return true;
    }
}
