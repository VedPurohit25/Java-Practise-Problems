class Solution {
    public int maxSubArray(int[] nums) {
        // Initialize our tracking aggregates with the first element's value
        int currentSum = nums[0];
        int maxSum = nums[0];

        // Single-pass linear scan starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // Decision: Add the current element to the running path,
            // or reset the subarray start point to the current element.
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            
            // Track the absolute highest peak achieved so far
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}