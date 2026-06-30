import java.util.Stack;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        // Iterate up to n to include a virtual trailing bar of height 0
        for (int i = 0; i <= n; i++) {
            // Assign a height of 0 to the virtual trailing element to flush out the stack
            int currentHeight = (i == n) ? 0 : heights[i];

            // Maintain the monotonic increasing property of the stack
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int poppedIndex = stack.pop();
                int height = heights[poppedIndex];
                
                // Determine the maximum horizontal expansion width for the popped height
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                
                // Calculate area and update our global maximum
                maxArea = Math.max(maxArea, height * width);
            }
            
            // Push the current index onto the stack
            stack.push(i);
        }

        return maxArea;
    }
}