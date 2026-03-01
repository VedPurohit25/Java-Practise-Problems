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


//solution using hash map 
import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        // Map to store: <PrefixSum, Number of times it occurred>
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Base case: a prefix sum of 0 has occurred once
        map.put(0, 1);
        
        for (int num : nums) {
            currentSum += num;
            
            // If (currentSum - k) exists, it means a subarray sum of k exists
            if (map.containsKey(currentSum - k)) {
                count += map.get(currentSum - k);
            }
            
            // Record this currentSum in the map
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
}
