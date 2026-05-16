import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();
        int n = nums.length;
        
        // Edge case: A quadruplet requires at least 4 distinct elements
        if (n < 4) return quadruplets;
        
        // Step 1: Sort the array to gain directional pointer control
        Arrays.sort(nums);
        
        // Step 2: Fix the first anchor pillar
        for (int i = 0; i < n - 3; i++) {
            // Avoid duplicate configurations at the first level
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            // Step 3: Fix the second anchor pillar
            for (int j = i + 1; j < n - 2; j++) {
                // Avoid duplicate configurations at the second level
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                
                int left = j + 1;
                int right = n - 1;
                
                // Step 4: Use a two-pointer window to sweep the remaining space
                while (left < right) {
                    // Cast to long to prevent 32-bit integer overflow
                    long sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        
                        // Prune duplicate values for both moving pointers
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++; // Move to a larger structural value
                    } else {
                        right--; // Move to a smaller structural value
                    }
                }
            }
        }
        
        return quadruplets;
    }
}