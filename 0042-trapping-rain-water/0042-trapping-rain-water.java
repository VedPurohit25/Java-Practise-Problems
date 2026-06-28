class Solution {
    public int trap(int[] height) {
        if (height == null || height.length <= 2) return 0;

        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWaterTrapped = 0;

        // Converge the pointers inward toward the highest peak of the terrain
        while (left < right) {
            if (height[left] <= height[right]) {
                // The left wall is the bottleneck
                if (height[left] >= leftMax) {
                    leftMax = height[left]; // Update the local left boundary
                } else {
                    totalWaterTrapped += leftMax - height[left]; // Add trapped volume
                }
                left++; // Step inward
            } else {
                // The right wall is the bottleneck
                if (height[right] >= rightMax) {
                    rightMax = height[right]; // Update the local right boundary
                } else {
                    totalWaterTrapped += rightMax - height[right]; // Add trapped volume
                }
                right--; // Step inward
            }
        }

        return totalWaterTrapped;
    }
}