class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxWater = 0;

        while (left < right) {
            // Calculate current width and the bottleneck height
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            
            // Compute current capacity and update the global maximum
            int currentWater = width * currentHeight;
            maxWater = Math.max(maxWater, currentWater);

            // Move the pointer that points to the shorter wall
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }
}