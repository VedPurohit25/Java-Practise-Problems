class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;

        // Create a 1D rolling array representing the minimum cost to reach each column
        int[] dp = new int[n];

        // Base Case: Initialize the starting cell
        dp[0] = grid[0][0];

        // Initialize the rest of the first row (can only be reached from the left)
        for (int c = 1; c < n; c++) {
            dp[c] = dp[c - 1] + grid[0][c];
        }

        // Iterate through the remaining rows from top to bottom
        for (int r = 1; r < m; r++) {
            // Process the first column of the current row (can only be reached from above)
            dp[0] = dp[0] + grid[r][0];

            // Process the remaining columns from left to right
            for (int c = 1; c < n; c++) {
                // dp[c] holds the cost from above, dp[c-1] holds the cost from the left
                dp[c] = grid[r][c] + Math.min(dp[c], dp[c - 1]);
            }
        }

        // Return the minimum path sum calculated at the bottom-right corner
        return dp[n - 1];
    }
}