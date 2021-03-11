package org.ivzh.tree;


// https://leetcode.com/problems/implement-trie-prefix-tree/
public class Trie {

    Trie[] trie = new Trie[26];
    boolean isEnd = false;

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("cofee");
        System.out.println(t.search("cofee"));
        System.out.println(t.startsWith("co"));
    }

    public void insert(String w) {
        Trie t = this;
        for (char c : w.toCharArray()) {
            int position = getAlphaBetPosition(c);
            if (t.trie[position] == null) {
                t.trie[position] = new Trie();
            }
            t = t.trie[position];
        }
        t.isEnd = true;
    }

    public boolean search(String w) {
        Trie t = this;
        for (char c : w.toCharArray()) {
            int position = getAlphaBetPosition(c);
            if (t.trie[position] == null) {
                return false;
            }
            t = t.trie[position];
        }
        return t.isEnd;
    }

    public boolean startsWith(String w) {
        Trie t = this;
        for (char c : w.toCharArray()) {
            int position = getAlphaBetPosition(c);
            if (t.trie[position] == null) {
                return false;
            }
            t = t.trie[position];
        }
        return true;
    }

    private int getAlphaBetPosition(char c) {
        return c - 'a';
    }
}
