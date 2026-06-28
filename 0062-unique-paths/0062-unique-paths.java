import java.util.Arrays;

class Solution {
    public int uniquePaths(int m, int n) {
        // Create a 1D array representing the active grid row path values
        int[] dp = new int[n];
        
        // Base Case: There is exactly 1 way to reach any cell in the first row
        Arrays.fill(dp, 1);

        // Iterate through the remaining rows from top to bottom
        for (int r = 1; r < m; r++) {
            // Traverse columns from left to right
            for (int c = 1; c < n; c++) {
                // dp[c] (above cell value) + dp[c - 1] (left cell value)
                dp[c] = dp[c] + dp[c - 1];
            }
        }

        // The final slot contains the total cumulative paths to the bottom-right corner
        return dp[n - 1];
    }
}