package org.ivzh.strings;

public class LongestPalindromeHash {

    private static long PRIME = 31;
    private static long MOD = (long)1e9+7;

    Integer maxLen = Integer.MIN_VALUE;
    Integer start=Integer.MIN_VALUE;


    public static void main(String[] args) {
        String s = "babad";
        String palindrome = new LongestPalindromeHash().longestPalindrome(s);
        System.out.println(palindrome);
    }

    public String longestPalindrome(String s) {
        Hash hash = new Hash(s, PRIME, MOD);
        Hash reverse = new Hash(new StringBuilder(s).reverse().toString(), PRIME, MOD);

        for (int i =0; i < s.length(); i++) {
            binSearchOdd(s, i, hash, reverse, start, maxLen);
        }

        for (int i =0; i < s.length(); i++) {
            binSearchEven(s, i, hash, reverse, start, maxLen);
        }

        return s.substring(start, maxLen);
    }

    private void binSearchOdd(String s, int i, Hash hash, Hash reverse, Integer start, Integer max) {
        int l = 0;
        int r = Math.min(s.length() - i, i);

        while (r - l > 1) {
            int middle = l + (r - l) / 2;
            if (hash.getHash(i - middle, i + middle -1)
                    == reverse.getHash(i - middle, i + middle-1)) {
                l = middle;
            } else {
                r = middle;
            }

            if(2*r-1 > max) {
                max = 2*r-1;
                start = i-r+1;
            }
        }
    }

    private void binSearchEven(String s, int i, Hash hash, Hash reverse, Integer start, Integer max) {
        int l = 0;
        int r = Math.min(s.length() - i, i);

        while (r - l > 1) {
            int middle = l + (r - l) / 2;
            if (hash.getHash(i - middle, i + middle -1)
                    == reverse.getHash(i - middle, i + middle-1)) {
                l = middle;
            } else {
                r = middle;
            }

            if(2*r > max) {
                max = 2*r;
                start = i-r;
            }
        }
    }

    static class Hash {
        private long[] hash;
        private long mod;
        private long p;

        public  Hash(String s, long p, long mod) {
            int n = s.length();
            this.hash = new long[n];
            this.mod = mod;
            this.p = p;

            char c = (char) (s.charAt(0) - 'A' + 1);
            hash[0] = (c * p) % MOD;
            for (int i = 1; i < n; i++) {
                c = s.charAt(i);
                c = (char) (c - 'A' + 1);
                this.hash[i] = (hash[i-1] * p * c) % MOD;
            }
        }

        public long getHash(int l, int r) {
            if (l == 0) {
                return this.hash[r];
            }

            return  (this.hash[r] - this.hash[l - 1] * (long) (Math.pow(p, r - l + 1))) % this.mod;
        }
    }
}
