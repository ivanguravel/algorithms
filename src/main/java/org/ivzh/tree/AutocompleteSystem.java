package org.ivzh.tree;

import java.util.HashSet;
import java.util.Set;

public class AutocompleteSystem {
    AutocompleteSystem[] trie = new AutocompleteSystem[26];
    boolean isEnd = false;
    String word = "";


    public static void main(String[] args) {
        AutocompleteSystem autocompleteSystem = new AutocompleteSystem();
        autocompleteSystem.insert("ab");
        autocompleteSystem.insert("abr");
        autocompleteSystem.insert("abrabacatabra");

        Set<String> strings = autocompleteSystem.search("abrab");
        for (String s : strings) {
            System.out.println(s);
        }

    }

    public void insert(String w) {
        char[] arr = w.toCharArray();
        AutocompleteSystem t = this;
        for (char c : arr) {
            int pos = getPosition(c);
            if (t.trie[pos] == null) {
                t.trie[pos] = new AutocompleteSystem();
            }
            t = t.trie[pos];
        }
        t.isEnd = true;
        t.word = w;
    }

    public Set<String> search(String w) {
        char[] arr = w.toCharArray();
        AutocompleteSystem t = this;
        for (char c : arr) {
            int pos = getPosition(c);
            if (t.trie[pos] != null) {
                t = t.trie[pos];
            }
        }
        Set<String> words = new HashSet<>();
        searchWords(t, words);
        return words;
    }

    private void searchWords(AutocompleteSystem t, Set<String> words) {
        if (t!= null) {
            if (t.isEnd) {
                words.add(t.word);
            }
            for (AutocompleteSystem system : t.trie) {
                searchWords(system, words);
            }

        }
    }

    private int getPosition(char c ) {
        return c - 'a';
    }
}
