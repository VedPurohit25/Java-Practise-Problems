class Solution {
    public int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int furthest = 0;
        
        // We don't need to jump from the last element
        for (int i = 0; i < nums.length - 1; i++) {
            // Discovery phase: What's the best next step available?
            furthest = Math.max(furthest, i + nums[i]);
            
            // If we've reached the end of our current jump's "reach"
            if (i == currentEnd) {
                jumps++;
                currentEnd = furthest;
                
                // Early exit if we can already touch the finish line
                if (currentEnd >= nums.length - 1) break;
            }
        }
        
        return jumps;
    }
}