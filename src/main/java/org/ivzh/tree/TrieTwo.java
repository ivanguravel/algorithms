package org.ivzh.tree;

import java.util.HashMap;
import java.util.Map;


// https://leetcode.com/problems/implement-trie-ii-prefix-tree
public class TrieTwo {

    private TrieNode root;

    static class TrieNode {

        public Map<Character, TrieNode> nodes = new HashMap();
        public int end = 0;
        public int count = 0;
    }

    public TrieTwo() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode start = root;
        for (char c : word.toCharArray()) {
            if (!start.nodes.containsKey(c)) {
                start.nodes.put(c, new TrieNode());
            }
            start = start.nodes.get(c);
            start.count = start.count + 1;
        }
        start.end = start.end +1;
    }

    public int countWordsEqualTo(String word) {
        TrieNode start = root;
        for (char c : word.toCharArray()) {
            if (start.nodes.containsKey(c)) {
                start = start.nodes.get(c);
            }
        }

        return start.end;
    }

    public int countWordsStartingWith(String prefix) {
        TrieNode start = root;
        for (char c : prefix.toCharArray()) {
            if (start.nodes.containsKey(c)) {
                start = start.nodes.get(c);
            }
        }

        return start.count;
    }

    public void erase(String word) {
        TrieNode start = root;
        for (char c : word.toCharArray()) {
            if (start.nodes.containsKey(c)) {
                start = start.nodes.get(c);
                start.count = start.count - 1;
            }

        }
        start.end = start.end -1;
    }
}
