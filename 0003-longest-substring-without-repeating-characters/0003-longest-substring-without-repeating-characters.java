import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        // Map to store: Character -> Its most recent index
        HashMap<Character, Integer> map = new HashMap<>();
        
        int left = 0;
        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);
            
            // If we've seen this character before AND it's inside our current window
            if (map.containsKey(currentChar)) {
                // Move the left border to the right of the previous occurrence
                left = Math.max(left, map.get(currentChar) + 1);
            }
            
            // Update the map with the current character's index
            map.put(currentChar, right);
            
            // Calculate the window size and update the maximum
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}