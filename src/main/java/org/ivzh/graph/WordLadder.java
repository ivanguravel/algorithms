pachage org.ivzh.graph;

import java.util.*;


// https://leetcode.com/problems/word-ladder/
class WordLadder {
  
  public int ladderLengthBestChoiseForUnderstanding(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (beginWord.equalsIgnoreCase(endWord) || !set.contains(endWord)) {
            return 0;
        }
        
        
        
        Queue<String> q = new ArrayDeque<>();
        
        q.add(beginWord);
        int level = 1;
        
        while(!q.isEmpty()) {
            int size = q.size();
            ++level;
            for (int i = 0; i < size; i++) {
                char[] word = q.poll().toCharArray();
                for (int position = 0; position < endWord.length(); position++) {
                    char origin = word[position];
                    for (char ch = 'a'; ch<='z';ch++) {
                        word[position] = ch;
                        String asStr = String.valueOf(word);
                        if (asStr.equals(endWord)) {
                            return level+1;
                        }
                        
                        if (!set.contains(endWord)) {
                            continue;
                        }
                        
                        set.remove(asStr);
                        q.add(asStr);
                        
                    }
                    word[position] = origin;
                }
            }
            
        }
        return 0;
    }
  
  public int ladderLength(String beginWord, String endWord, List<String> words) {
        Deque<String> queue = new ArrayDeque<>();
        Map<String,Integer> minDist = new HashMap<>();
        
        queue.add(beginWord);
        minDist.put(beginWord, 1);
        
        while (!queue.isEmpty()) {
            String word = queue.peek();
            queue.pop();
            
            if (word.equals(endWord)) {
                break; 
            }
            
            for (String nextWord : words) {
                if (numOfChanges(word, nextWord) != 1) {
                    continue;
                }
                if (minDist.containsKey(nextWord)) {
                    continue;
                }
                minDist.put(nextWord, minDist.get(word) + 1);
                queue.add(nextWord);
            }
        }
        return minDist.getOrDefault(endWord, 0);
    }
  
  int numOfChanges(String first, String second) {
        int differences = 0;
        for (int i = 0; i < first.length(); ++i) {
            differences += (first.charAt(i) != second.charAt(i)) ? 1 : 0;
        }
        return differences;
    }
}
