import java.util.Stack;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxMaximalArea = 0;

        // Flatten the 2D matrix into sequential 1D histogram slices
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < cols; c++) {
                // Accumulate height if '1', reset to 0 if the continuity breaks
                if (matrix[r][c] == '1') {
                    heights[c] += 1;
                } else {
                    heights[c] = 0;
                }
            }

            // Compute the maximum rectangle possible using the current row as the floor baseline
            maxMaximalArea = Math.max(maxMaximalArea, calculateHistogramArea(heights));
        }

        return maxMaximalArea;
    }

    // Monotonic Increasing Stack Engine to calculate largest rectangle in O(cols) time
    private int calculateHistogramArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int poppedIndex = stack.pop();
                int height = heights[poppedIndex];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }
}