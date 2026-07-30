import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count frequency of each character
        Integer[] count = new Integer[26];
        Arrays.fill(count, 0);
        
        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Step 2: Sort frequencies in descending order
        Arrays.sort(count, Collections.reverseOrder());
        
        int totalPushes = 0;
        
        // Step 3: Calculate the minimum pushes greedily
        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) {
                break; // No more characters present in the word
            }
            // The first 8 characters need 1 push, next 8 need 2, etc.
            int multiplier = (i / 8) + 1;
            totalPushes += count[i] * multiplier;
        }
        
        return totalPushes;
    }
}