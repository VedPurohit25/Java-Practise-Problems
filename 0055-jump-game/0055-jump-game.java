class Solution {
    public boolean canJump(int[] nums) {
        int reachable = 0;
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            // If the current index is beyond our furthest reach, we are stuck
            if (i > reachable) {
                return false;
            }
            
            // Update the furthest index we can reach from here
            reachable = Math.max(reachable, i + nums[i]);
            
            // Optimization: If we can already reach the end, stop early
            if (reachable >= n - 1) {
                return true;
            }
        }
        
        return true;
    }
}