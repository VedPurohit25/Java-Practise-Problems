import java.util.HashMap;
import java.util.Map;

class Solution {
    // Memoization cache mapping string pairs "s1_s2" to their scrambled boolean outcome
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        // Base Case 1: Identical strings are always valid scrambles
        if (s1.equals(s2)) return true;
        
        // Base Case 2: Mismatched lengths cannot be scrambled matches
        if (s1.length() != s2.length()) return false;

        String cacheKey = s1 + "_" + s2;
        if (memo.containsKey(cacheKey)) {
            return memo.get(cacheKey);
        }

        // Optimization: Anagram Check. Prune the branch if character frequencies mismatch
        int[] freq = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
            freq[s2.charAt(i) - 'a']--;
        }
        for (int count : freq) {
            if (count != 0) {
                memo.put(cacheKey, false);
                return false;
            }
        }

        int n = s1.length();
        // Evaluate all possible binary split locations
        for (int i = 1; i < n; i++) {
            // Scenario 1: The two chunks were NOT swapped at this split point
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && 
                isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(cacheKey, true);
                return true;
            }

            // Scenario 2: The two chunks WERE swapped at this split point
            if (isScramble(s1.substring(0, i), s2.substring(n - i), s2) && 
                isScramble(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(cacheKey, true);
                return true;
            }
        }

        memo.put(cacheKey, false);
        return false;
    }

    // Helper overload to route the swapped substring check cleanly
    private boolean isScramble(String sub1, String sub2, String rootRef) {
        return isScramble(sub1, sub2);
    }
}