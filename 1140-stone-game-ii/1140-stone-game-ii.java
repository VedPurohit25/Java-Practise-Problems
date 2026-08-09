class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // Compute suffix sums where suffixSum[i] is the sum of piles from i to n - 1
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        // dp[i][M] stores the maximum stones the current player can take 
        // starting from index i with parameter M
        int[][] dp = new int[n][n + 1];
        
        return get組み合わせ(0, 1, suffixSum, dp, n);
    }
    
    private int get組み合わせ(int i, int M, int[] suffixSum, int[][] dp, int n) {
        // Base case: If remaining piles are <= 2 * M, take all of them
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }
        
        // Return cached result if already calculated
        if (dp[i][M] != 0) {
            return dp[i][M];
        }
        
        int maxStones = 0;
        
        // Try taking X piles where 1 <= X <= 2 * M
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            
            // Opponent's best score from remaining piles starting at i + X
            int opponentStones = get組み合わせ(i + X, nextM, suffixSum, dp, n);
            
            // Current player's score is (total remaining stones) - (opponent's best score)
            maxStones = Math.max(maxStones, suffixSum[i] - opponentStones);
        }
        
        dp[i][M] = maxStones;
        return maxStones;
    }
}