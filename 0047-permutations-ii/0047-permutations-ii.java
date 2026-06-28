import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Step 1: Sort the array to group identical elements together
        Arrays.sort(nums);
        
        // Track whether an element at index 'i' is currently being used in the active path
        boolean[] used = new boolean[nums.length];
        
        // Step 2: Ignite the backtracking sequence
        backtrack(result, new ArrayList<>(), nums, used);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentPath, int[] nums, boolean[] used) {
        // Base Case: When the path length matches the input length, a unique configuration is complete
        if (currentPath.size() == nums.length) {
            result.add(new ArrayList<>(currentPath));
            return;
        }

        // Iterate through all possible elements for the current permutation slot
        for (int i = 0; i < nums.length; i++) {
            // Skip if this exact array element has already been locked into an earlier slot
            if (used[i]) {
                continue;
            }

            // CRITICAL DUPLICATE PRUNING:
            // If the current element is identical to the previous element, and the previous 
            // element was NOT used in the immediate parent slot (!used[i - 1]), it means 
            // the previous element was already processed and popped during a previous sibling branch.
            // Continuing here would create an identical redundant tree layout.
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // Choice: Lock this element into the current slot position
            used[i] = true;
            currentPath.add(nums[i]);

            // Move forward to fill out the subsequent slot position
            backtrack(result, currentPath, nums, used);

            // Backtrack: Release the slot configuration for alternative peer iterations
            used[i] = false;
            currentPath.remove(currentPath.size() - 1);
        }
    }
}