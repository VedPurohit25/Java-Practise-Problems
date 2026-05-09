class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int numLayers = Math.min(m, n) / 2;

        for (int layer = 0; layer < numLayers; layer++) {
            // 1. Extract the layer into a 1D list
            List<Integer> elements = new ArrayList<>();
            
            // Top row
            for (int j = layer; j < n - 1 - layer; j++) elements.add(grid[layer][j]);
            // Right column
            for (int i = layer; i < m - 1 - layer; i++) elements.add(grid[i][n - 1 - layer]);
            // Bottom row
            for (int j = n - 1 - layer; j > layer; j--) elements.add(grid[m - 1 - layer][j]);
            // Left column
            for (int i = m - 1 - layer; i > layer; i--) elements.add(grid[i][layer]);

            // 2. Rotate the 1D list
            int totalElements = elements.size();
            int netRotation = k % totalElements;
            
            // 3. Put elements back in counter-clockwise order
            int index = netRotation; // Start from the k-th element to simulate rotation
            
            // Top row
            for (int j = layer; j < n - 1 - layer; j++) grid[layer][j] = elements.get(index++ % totalElements);
            // Right column
            for (int i = layer; i < m - 1 - layer; i++) grid[i][n - 1 - layer] = elements.get(index++ % totalElements);
            // Bottom row
            for (int j = n - 1 - layer; j > layer; j--) grid[m - 1 - layer][j] = elements.get(index++ % totalElements);
            // Left column
            for (int i = m - 1 - layer; i > layer; i--) grid[i][layer] = elements.get(index++ % totalElements);
        }

        return grid;
    }
}