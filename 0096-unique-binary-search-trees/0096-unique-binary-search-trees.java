class Solution {
    public int numTrees(int n) {
        // dp[i] will store the number of unique BSTs that can be formed with i nodes
        int[] dp = new int[n + 1];
        
        // Base cases
        dp[0] = 1;
        dp[1] = 1;
        
        // Iteratively compute Catalan numbers from 2 up to n
        for (int nodes = 2; nodes <= n; nodes++) {
            for (int root = 1; root <= nodes; root++) {
                // Left subtree elements: root - 1
                // Right subtree elements: nodes - root
                dp[nodes] += dp[root - 1] * dp[nodes - root];
            }
        }
        
        return dp[n];
    }
}