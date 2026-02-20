 class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int m = matrix.length;    // Number of rows
        int n = matrix[0].length; // Number of columns
        
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Map the 1D 'mid' back to 2D row and col
            int midValue = matrix[mid / n][mid % n];

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        System.out.println("Target 3 found: " + sol.searchMatrix(matrix, 3));  // true
        System.out.println("Target 13 found: " + sol.searchMatrix(matrix, 13)); // false
    }
}