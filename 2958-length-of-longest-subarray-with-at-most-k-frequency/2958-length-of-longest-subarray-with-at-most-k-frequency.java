import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        
        // Expand the sliding window with the right pointer
        for (int right = 0; right < nums.length; right++) {
            int current = nums[right];
            freqMap.put(current, freqMap.getOrDefault(current, 0) + 1);
            
            // Shrink window from the left if current element's frequency exceeds k
            while (freqMap.get(current) > k) {
                int leftVal = nums[left];
                freqMap.put(leftVal, freqMap.get(leftVal) - 1);
                left++;
            }
            
            // Update the maximum length of a valid good subarray
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}