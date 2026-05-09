class Solution {
    public int[] maxValue(int[] nums) { // Changed from maxReach to maxValue
        int n = nums.length;
        int[] ans = new int[n];
        
        // Step 1: Pre-calculate the Prefix Maximums
        int[] preMax = new int[n];
        preMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }
        
        // Step 2: Suffix Minimum tracking and reachability
        int sufMin = Integer.MAX_VALUE;
        
        ans[n - 1] = preMax[n - 1];
        sufMin = nums[n - 1];
        
        for (int i = n - 2; i >= 0; i--) {
            if (preMax[i] > sufMin) {
                // Bridge exists: reach what the next index reaches
                ans[i] = ans[i + 1];
            } else {
                // No bridge: capped at current prefix max
                ans[i] = preMax[i];
            }
            sufMin = Math.min(sufMin, nums[i]);
        }
        
        return ans;
    }
}