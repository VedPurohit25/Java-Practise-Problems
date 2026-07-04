import java.util.*;

class Solution {
    // Memoization map to store the valid sentences formed by a suffix string
    private Map<String, List<String>> memo = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        return backtrack(s, wordSet);
    }

    private List<String> backtrack(String s, Set<String> wordSet) {
        // If we have already solved for this substring, return the cached result
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> results = new ArrayList<>();

        // Base case: if the string itself is a valid word, add it to results
        if (wordSet.contains(s)) {
            results.add(s);
        }

        // Try partitioning the string at every possible index
        for (int i = 1; i < s.length(); i++) {
            String prefix = s.substring(0, i);

            // If the prefix is a valid dictionary word, solve for the suffix
            if (wordSet.contains(prefix)) {
                String suffix = s.substring(i);
                List<String> suffixSegments = backtrack(suffix, wordSet);

                // Combine the current prefix with all valid configurations of the suffix
                for (String segment : suffixSegments) {
                    results.add(prefix + " " + segment);
                }
            }
        }

        // Cache the result for the current string before returning
        memo.put(s, results);
        return results;
    }
}