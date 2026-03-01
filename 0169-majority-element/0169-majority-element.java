import java.util.HashMap;
import java.util.Map;

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        int n = nums.length;
        
        for (int num : nums) {
            // Update the count for the current number
            int currentCount = counts.getOrDefault(num, 0) + 1;
            counts.put(num, currentCount);
            
            // If this number appears more than n/2 times, it's the winner
            if (currentCount > n / 2) {
                return num;
            }
        }
        
        return -1; // Should not reach here per constraints
    }
}