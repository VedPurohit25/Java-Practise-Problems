class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Initialize tracking variables with the first element
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int globalMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];

            // Temporary variable to hold maxSoFar before it gets updated
            int tempMax = Math.max(curr, Math.max(maxSoFar * curr, minSoFar * curr));
            
            // Update minSoFar using the original maxSoFar value
            minSoFar = Math.min(curr, Math.min(maxSoFar * curr, minSoFar * curr));
            
            maxSoFar = tempMax;

            // Keep track of the highest product found overall
            globalMax = Math.max(globalMax, maxSoFar);
        }

        return globalMax;
    }
}