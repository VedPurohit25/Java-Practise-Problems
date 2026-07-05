class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;

        // dp[r][c][0] stores the max sum to reach (r, c)
        // dp[r][c][1] stores the number of paths to reach (r, c) with that max sum
        int[][][] dp = new int[n][n][2];

        // Initialize DP table: -1 for sum indicates unreachable, 0 paths
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = -1; 
                dp[i][j][1] = 0;
            }
        }

        // Base case: Start at the bottom-right corner
        dp[n - 1][n - 1][0] = 0;
        dp[n - 1][n - 1][1] = 1;

        // Directions to look from (r, c): Down, Right, Down-Right
        int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};

        // Fill the DP table from bottom-right to top-left
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                // Skip if it's an obstacle or the starting point (already initialized)
                if (board.get(r).charAt(c) == 'X' || (r == n - 1 && c == n - 1)) {
                    continue;
                }

                int maxScore = -1;
                int pathCount = 0;

                // Check all 3 incoming directions
                for (int[] d : dirs) {
                    int prevR = r + d[0];
                    int prevC = c + d[1];

                    // Check bounds and if the previous cell was reachable
                    if (prevR < n && prevC < n && dp[prevR][prevC][0] != -1) {
                        int score = dp[prevR][prevC][0];
                        int count = dp[prevR][prevC][1];

                        if (score > maxScore) {
                            maxScore = score;
                            pathCount = count;
                        } else if (score == maxScore) {
                            pathCount = (pathCount + count) % MOD;
                        }
                    }
                }

                // If at least one valid incoming path exists, update the current cell
                if (maxScore != -1) {
                    char currChar = board.get(r).charAt(c);
                    int currVal = (currChar == 'E') ? 0 : (currChar - '0');
                    
                    dp[r][c][0] = maxScore + currVal;
                    dp[r][c][1] = pathCount;
                }
            }
        }

        // If the destination 'E' (0,0) is unreachable, return [0, 0]
        if (dp[0][0][0] == -1) {
            return new int[]{0, 0};
        }

        return dp[0][0];
    }
}