class Solution {
    public int[][] generateMatrix(int n) {
        // Instantiate an empty n x n matrix grid
        int[][] matrix = new int[n][n];
        
        // Define our four dynamic spatial boundaries
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        
        // Initialize the tracking sequential counter
        int val = 1;
        int target = n * n;

        // Continue looping until every cell is populated
        while (val <= target) {
            
            // Vector 1: Fill left-to-right along the active top boundary
            for (int i = left; i <= right; i++) {
                matrix[top][i] = val++;
            }
            top++; // Compress top boundary downward

            // Vector 2: Fill top-to-bottom along the active right boundary
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = val++;
            }
            right--; // Compress right boundary leftward

            // Vector 3: Fill right-to-left along the active bottom boundary
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = val++;
            }
            bottom--; // Compress bottom boundary upward

            // Vector 4: Fill bottom-to-top along the active left boundary
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = val++;
            }
            left++; // Compress left boundary rightward
        }

        return matrix;
    }
}