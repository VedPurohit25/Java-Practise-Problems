import java.util.*;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Convert the list to a Set for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordDict);
        
        // dp[i] represents if s.substring(0, i) can be segmented
        boolean[] dp = new boolean[s.length() + 1];
        
        // Base case: an empty string is always valid
        dp[0] = true;
        
        // Iterate through all possible lengths of the substring
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // If the prefix s[0...j] is valid AND s[j...i] is a dictionary word
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // Move to the next i once a valid segmentation is found
                }
            }
        }
        
        return dp[s.length()];
    }
}