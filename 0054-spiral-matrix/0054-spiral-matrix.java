import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return result;

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        // Continue running circuits until boundaries completely intersect/overlap
        while (top <= bottom && left <= right) {
            
            // Vector 1: Move left-to-right along the active top boundary
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++; // Compress top boundary downward

            // Vector 2: Move top-to-bottom along the active right boundary
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // Compress right boundary leftward

            // Vector 3: Move right-to-left along the active bottom boundary (Verify Row Constraint)
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--; // Compress bottom boundary upward
            }

            // Vector 4: Move bottom-to-top along the active left boundary (Verify Column Constraint)
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // Compress left boundary rightward
            }
        }

        return result;
    }
}