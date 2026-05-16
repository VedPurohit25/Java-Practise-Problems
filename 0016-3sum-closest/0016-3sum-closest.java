import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        
        // Step 1: Sort the array to gain directional pointer control
        Arrays.sort(nums);
        
        // Initialize with the first possible combination sum
        int closestSum = nums[0] + nums[1] + nums[2];
        
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            
            // Step 2: Use two pointers to dynamically tighten the proximity window
            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];
                
                // If we hit the exact target, no closer sum is mathematically possible
                if (currentSum == target) {
                    return currentSum;
                }
                
                // If the current combination is closer to target than our previous record
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }
                
                // Direct the pointers based on relative positioning to target
                if (currentSum < target) {
                    left++; // Move to a larger structural value
                } else {
                    right--; // Move to a smaller structural value
                }
            }
        }
        
        return closestSum;
    }
}