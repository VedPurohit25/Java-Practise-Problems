class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Edge Case: If the entry or exit point is blocked, no path can exist
        if (obstacleGrid == null || obstacleGrid[0][0] == 1) {
            return 0;
        }

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // Create a 1D rolling array representing path values across columns
        int[] dp = new int[n];
        
        // Base Case: Anchor the start coordinate path source
        dp[0] = 1;

        // Iterate through every cell in the grid row-by-row
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // Constraint Check: If an obstacle is hit, set paths to 0
                if (obstacleGrid[r][c] == 1) {
                    dp[c] = 0;
                } else if (c > 0) {
                    // Accumulate: Add the inflow from the left neighbor (dp[c-1])
                    // to the current cell value (which holds the inflow from above)
                    dp[c] += dp[c - 1];
                }
            }
        }

        // Return the final accumulated path configurations at the exit coordinate
        return dp[n - 1];
    }
}