import java.util.Scanner;

public class Solution {
    /**
     * Primary function to count islands in a 2D grid.
     * Time Complexity: O(M * N)
     * Space Complexity: O(M * N) (for the recursion stack)
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int islandCount = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Traverse the entire grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // When we find land ('1'), it's a new island
                if (grid[r][c] == '1') {
                    islandCount++;
                    // Trigger DFS to "sink" the entire island
                    dfs(grid, r, c);
                }
            }
        }
        return islandCount;
    }
    
    private void dfs(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Base Case: Check boundaries and if the cell is water ('0')
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == '0') {
            return;
        }
        
        // Mark current cell as visited by changing it to '0'
        grid[r][c] = '0';
        
        // Visit all adjacent neighbors (Up, Down, Left, Right)
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }

    // Main method for local testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println("Number of Islands: " + sol.numIslands(grid));
    }
}