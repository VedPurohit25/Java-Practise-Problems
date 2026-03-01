class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        
        // Try every possible starting position
        for (int i = 0; i < nums.length; i++) {
            int currentSum = 0;
            
            // For each start, try every possible ending position
            for (int j = i; j < nums.length; j++) {
                currentSum += nums[j];
                
                if (currentSum == k) {
                    count++;
                }
            }
        }
        
        return count;
    }
}