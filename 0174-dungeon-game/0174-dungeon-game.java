public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        // dp array representing the minimum health needed from the current position to the end
        int[] dp = new int[n];

        // Base case: Initialize the bottom-right corner
        dp[n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);

        // Fill the last row (can only move right)
        for (int j = n - 2; j >= 0; j--) {
            dp[j] = Math.max(1, dp[j + 1] - dungeon[m - 1][j]);
        }

        // Fill the rest of the grid from bottom to top
        for (int i = m - 2; i >= 0; i--) {
            // Last column of current row (can only move down)
            dp[n - 1] = Math.max(1, dp[n - 1] - dungeon[i][n - 1]);

            // Remaining columns (can move right or down)
            for (int j = n - 2; j >= 0; j--) {
                int minHealthOnExit = Math.min(dp[j], dp[j + 1]);
                dp[j] = Math.max(1, minHealthOnExit - dungeon[i][j]);
            }
        }

        // The answer is the health required to enter the top-left room
        return dp[0];
    }
}