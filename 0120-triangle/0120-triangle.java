import java.util.List;

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        
        // DP array initialized to match the size of the widest (bottom) row
        // This will hold the rolling minimum path sums
        int[] dp = new int[n];
        
        // Seed the DP array with the baseline values from the bottom row
        List<List<Integer>> lastRow = triangle;
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }
        
        // Collapse the states bottom-up, starting from the second-to-last row (n - 2)
        for (int row = n - 2; row >= 0; row--) {
            List<Integer> currentRow = triangle.get(row);
            
            // Update each valid column index for the current row level
            for (int col = 0; col <= row; col++) {
                // Optimal Choice: Current value + min of its two lower adjacent choices
                dp[col] = currentRow.get(col) + Math.min(dp[col], dp[col + 1]);
            }
        }
        
        // The apex of the triangle now holds the absolute minimum path sum
        return dp[0];
    }
}