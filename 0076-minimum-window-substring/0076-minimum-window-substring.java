import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Step 1: Map the exact character profile frequencies required by t
        Map<Character, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        // Distinct characters that must match target frequencies
        int requiredCount = targetMap.size();
        
        // Window state parameters
        Map<Character, Integer> windowMap = new HashMap<>();
        int left = 0;
        int right = 0;
        int formedCount = 0;

        // Tracker to retain the structural coordinates of the minimum valid window
        // Format: [window_length, start_index, end_index]
        int[] minWindowTrack = {-1, 0, 0};

        // Step 2: Expand the right boundary across the stream
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            windowMap.put(rightChar, windowMap.getOrDefault(rightChar, 0) + 1);

            // If the current window matches the required target profile for this character
            if (targetMap.containsKey(rightChar) && 
                windowMap.get(rightChar).equals(targetMap.get(rightChar))) {
                formedCount++;
            }

            // Step 3: Contract the window from the left once fully satisfied
            while (left <= right && formedCount == requiredCount) {
                char leftChar = s.charAt(left);

                // Update the minimum window dimensions if this new path is tighter
                int currentWindowSize = right - left + 1;
                if (minWindowTrack[0] == -1 || currentWindowSize < minWindowTrack[0]) {
                    minWindowTrack[0] = currentWindowSize;
                    minWindowTrack[1] = left;
                    minWindowTrack[2] = right;
                }

                // Discard the leftmost character and compress the left pointer
                windowMap.put(leftChar, windowMap.get(leftChar) - 1);
                
                // If discarding this character breaks our target profile threshold
                if (targetMap.containsKey(leftChar) && 
                    windowMap.get(leftChar) < targetMap.get(leftChar)) {
                    formedCount--;
                }

                left++; // Shrink window
            }

            right++; // Expand window
        }

        // Return the exact substring slice or empty string if no valid window was mapped
        return minWindowTrack[0] == -1 ? "" : s.substring(minWindowTrack[1], minWindowTrack[2] + 1);
    }
}