class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        
        // Rolling DP variables representing dp[i+1], dp[i+2], and dp[i+3]
        int dp1 = 0, dp2 = 0, dp3 = 0;
        
        // Process backward from the last stone to the first
        for (int i = n - 1; i >= 0; i--) {
            int currentTake = 0;
            int maxDiff = Integer.MIN_VALUE;
            
            // Try taking 1, 2, or 3 stones
            for (int k = 0; k < 3 && i + k < n; k++) {
                currentTake += stoneValue[i + k];
                
                // Get opponent's best response from remaining stones
                int nextDp = (k == 0) ? dp1 : (k == 1) ? dp2 : dp3;
                
                maxDiff = Math.max(maxDiff, currentTake - nextDp);
            }
            
            // Shift rolling variables for the next iteration step
            dp3 = dp2;
            dp2 = dp1;
            dp1 = maxDiff;
        }
        
        // dp1 now holds dp[0], the best score difference Alice can achieve
        if (dp1 > 0) {
            return "Alice";
        } else if (dp1 < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}