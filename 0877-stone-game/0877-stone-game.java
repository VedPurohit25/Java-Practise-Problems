class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        // dp[i][j] represents the maximum score difference (Alice - Bob) 
        // for the subarray piles[i...j]
        int[][] dp = new int[n][n];
        
        // Base cases: single pile left
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }
        
        // Build up for subarrays of length 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        
        return dp[0][n - 1] > 0;
    }
}