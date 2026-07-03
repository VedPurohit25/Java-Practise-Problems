import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 1. Put all dictionary words into a fast-lookup HashSet
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0; // If target word is missing from the dictionary, it's impossible
        }
        
        // 2. Initialize the two frontiers for bidirectional BFS
        Set<String> forwardSet = new HashSet<>();
        Set<String> backwardSet = new HashSet<>();
        
        forwardSet.add(beginWord);
        backwardSet.add(endWord);
        
        // Track the sequence length (we count the words, so we start at 1)
        int step = 1;
        int wordLen = beginWord.length();
        
        // 3. Process the frontiers
        while (!forwardSet.isEmpty() && !backwardSet.isEmpty()) {
            // Always expand the smaller frontier to minimize branching operations
            if (forwardSet.size() > backwardSet.size()) {
                Set<String> temp = forwardSet;
                forwardSet = backwardSet;
                backwardSet = temp;
            }
            
            Set<String> nextLevelSet = new HashSet<>();
            
            // Remove current frontier words from dict to avoid circular path re-visiting
            for (String word : forwardSet) {
                dict.remove(word);
            }
            
            // Expand the current frontier by mutating characters
            for (String word : forwardSet) {
                char[] chars = word.toCharArray();
                
                for (int i = 0; i < wordLen; i++) {
                    char originalChar = chars[i];
                    
                    // Try changing the current character to every lowercase letter
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        
                        chars[i] = c;
                        String nextWord = new String(chars);
                        
                        // If the sets meet, the path is complete
                        if (backwardSet.contains(nextWord)) {
                            return step + 1;
                        }
                        
                        // If it's a valid next hop, queue it for the next level
                        if (dict.contains(nextWord)) {
                            nextLevelSet.add(nextWord);
                        }
                    }
                    chars[i] = originalChar; // Restore character
                }
            }
            
            // Move to the next depth layer
            forwardSet = nextLevelSet;
            step++;
        }
        
        return 0; // Return 0 if the two frontiers never intersect
    }
}