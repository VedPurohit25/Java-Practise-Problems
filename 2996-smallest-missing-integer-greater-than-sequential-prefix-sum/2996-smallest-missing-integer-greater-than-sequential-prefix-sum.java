import java.util.*;

class Solution {
    public int missingInteger(int[] nums) {
        int prefixSum = nums[0];
        int n = nums.length;
        
        // 1. Calculate the sum of the longest sequential prefix
        for (int i = 1; i < n; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                prefixSum += nums[i];
            } else {
                break;
            }
        }
        
        // 2. Store all elements in a HashSet for fast lookup
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        // 3. Find the smallest integer >= prefixSum missing from nums
        int x = prefixSum;
        while (numSet.contains(x)) {
            x++;
        }
        
        return x;
    }
}