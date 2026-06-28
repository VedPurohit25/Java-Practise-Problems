import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Launch the backtracking engine starting at position 0
        backtrack(result, nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, int[] nums, int start) {
        // Base Case: All positions have been determined, saving the configuration
        if (start == nums.length) {
            List<String> list = new ArrayList<>(); // Creating a fresh snapshot list
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }

        // Iterate through all remaining candidate elements for the 'start' position
        for (int i = start; i < nums.length; i++) {
            // Choice: Swap the current element into the active 'start' anchor slot
            swap(nums, start, i);
            
            // Move forward to determine the placement for the next position
            backtrack(result, nums, start + 1);
            
            // Backtrack: Undo the swap to restore the array layout for the next iteration
            swap(nums, start, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}