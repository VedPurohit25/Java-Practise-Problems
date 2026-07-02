class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // Edge optimization: if target is longer than source, matching is impossible
        if (m < n) {
            return 0;
        }

        // dp[j] stores the number of distinct subsequences matching prefix t[0...j-1]
        int[] dp = new int[n + 1];
        
        // Base case: an empty target string t can always be matched 1 way
        dp[0] = 1;

        // Traverse through each character of the source string s
        for (int i = 1; i <= m; i++) {
            char sChar = s.charAt(i - 1);
            
            // Traverse backward through target string t to preserve past grid state
            for (int j = n; j >= 1; j--) {
                if (sChar == t.charAt(j - 1)) {
                    // Match found: Sum up the ways including and excluding sChar
                    dp[j] = dp[j] + dp[j - 1];
                }
                // If they don't match, dp[j] simply remains unchanged (carries over dp[i-1][j])
            }
        }

        return dp[n];
    }
}