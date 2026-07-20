import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        
        // Optimize k to avoid unnecessary full rotations
        k = k % totalElements;
        
        // Initialize the result structure
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            result.add(new ArrayList<>(n));
            for (int j = 0; j < n; j++) {
                result.get(i).add(0); // Fill with placeholders
            }
        }
        
        // Map each element directly to its new position
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int old1DIndex = i * n + j;
                int new1DIndex = (old1DIndex + k) % totalElements;
                
                int newRow = new1DIndex / n;
                int newCol = new1DIndex % n;
                
                result.get(newRow).set(newCol, grid[i][j]);
            }
        }
        
        return result;
    }
}