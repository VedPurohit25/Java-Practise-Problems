import java.util.*;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Outer loop picks the first number
        for (int i = 0; i < nums.length; i++) {
            // Inner loop checks every number AFTER 'i'
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return new int[] {}; // No solution found
    }
}
