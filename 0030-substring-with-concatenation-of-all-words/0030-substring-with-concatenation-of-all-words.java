import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) {
            return result;
        }

        // FIX: Added parentheses to invoke the length() method on the String object
        int wordLen = words[0].length(); 
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;
        int sLen = s.length();

        // Build the reference frequency map for the target words
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // Run the sliding window for each possible word-length offset
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            Map<String, Integer> currentFreq = new HashMap<>();
            int count = 0; // Tracks the number of valid words currently in the window

            while (right + wordLen <= sLen) {
                // Extract the next word token from the right edge
                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (wordFreq.containsKey(word)) {
                    currentFreq.put(word, currentFreq.getOrDefault(word, 0) + 1);
                    count++;

                    // If we have more copies of this word than allowed, shrink from the left
                    while (currentFreq.get(word) > wordFreq.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        currentFreq.put(leftWord, currentFreq.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // If the count matches the total words required, we found a permutation
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // Invalid word encountered: completely reset the window state
                    currentFreq.clear();
                    count = 0;
                    left = right; 
                }
            }
        }

        return result;
    }
}