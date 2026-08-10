class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        
        // Build DP states from 1 to n
        for (int i = 1; i <= n; i++) {
            // Try subtracting all valid perfect squares k^2 <= i
            for (int k = 1; k * k <= i; k++) {
                if (!dp[i - k * k]) {
                    dp[i] = true; // Found a move that forces opponent to lose
                    break;
                }
            }
        }
        
        return dp[n];
    }
}