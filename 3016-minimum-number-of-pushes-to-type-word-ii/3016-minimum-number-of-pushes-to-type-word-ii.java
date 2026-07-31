import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int minimumPushes(String word) {
        // Step 1: Count the frequency of each letter
        Integer[] count = new Integer[26];
        Arrays.fill(count, 0);
        
        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Step 2: Sort frequencies in descending order
        Arrays.sort(count, Collections.reverseOrder());
        
        int totalPushes = 0;
        
        // Step 3: Calculate the minimum pushes
        for (int i = 0; i < 26; i++) {
            if (count[i] == 0) {
                break; // No more characters present in the word
            }
            // Assign keys in chunks of 8:
            // First 8 get multiplier 1, next 8 get multiplier 2, etc.
            int multiplier = (i / 8) + 1;
            totalPushes += count[i] * multiplier;
        }
        
        return totalPushes;
    }
}