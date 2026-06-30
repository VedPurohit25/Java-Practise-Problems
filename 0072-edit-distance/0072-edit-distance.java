class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // Create a 1D rolling array representing edit distances for prefixes of word2
        int[] dp = new int[n + 1];

        // Base Case: Converting an empty word1 to word2 requires 'j' insertions
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        // Iterate through each character of word1
        for (int i = 1; i <= m; i++) {
            // Store the base case for the left wall: converting word1[0...i-1] to empty word2
            int prev = dp[0]; 
            dp[0] = i; // Updating the zero-column value (i deletions)

            for (int j = 1; j <= n; j++) {
                // Temporarily cache the current value before it gets overwritten
                int temp = dp[j];

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // Match Case: Inherit the diagonal top-left cost directly
                    dp[j] = prev;
                } else {
                    // Mismatch Case: 1 + min(Replace [prev], Delete [dp[j]], Insert [dp[j-1]])
                    dp[j] = 1 + Math.min(prev, Math.min(dp[j], dp[j - 1]));
                }

                // Move the cached value to 'prev' to act as the diagonal top-left for the next cell
                prev = temp;
            }
        }

        return dp[n];
    }
}