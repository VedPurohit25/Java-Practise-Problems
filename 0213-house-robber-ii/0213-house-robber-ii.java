public class Solution {
    public int rob(int[] nums) {
        // Edge cases
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // Return the maximum of scenario 1 (exclude last) and scenario 2 (exclude first)
        return Math.max(
            robLinear(nums, 0, nums.length - 2), 
            robLinear(nums, 1, nums.length - 1)
        );
    }
    
    // Helper method to solve the standard linear House Robber problem
    private int robLinear(int[] nums, int start, int end) {
        int prev2 = 0; // Represents max money from 2 houses ago
        int prev1 = 0; // Represents max money from 1 house ago
        
        for (int i = start; i <= end; i++) {
            // Recurrence relation: max(skip current house, rob current house)
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
}