import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        
        // Step 1: Sort the array to enable two-pointer structural movement
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 2; i++) {
            // Optimization: If the anchor is positive, no triplet can sum to 0
            if (nums[i] > 0) break;
            
            // Skip duplicate values for the anchor to prevent identical triplets
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int left = i + 1;
            int right = n - 1;
            
            // Step 2: Perform a two-pointer scan for the remaining two elements
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates for the left pointer
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // Skip duplicates for the right pointer
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    // Move both pointers inward
                    left++;
                    right--;
                } else if (sum < 0) {
                    // Sum is too small, move left pointer to a larger value
                    left++;
                } else {
                    // Sum is too large, move right pointer to a smaller value
                    right--;
                }
            }
        }
        
        return result;
    }
}