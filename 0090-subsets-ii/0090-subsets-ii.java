import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Step 1: Sort the array to bring duplicate elements into adjacent slots
        Arrays.sort(nums);
        
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> currentPath, List<List<Integer>> result) {
        // Every state reached in the decision tree represents a valid unique subset
        result.add(new ArrayList<>(currentPath));

        for (int i = start; i < nums.length; i++) {
            // Step 2: Skip duplicates. If nums[i] matches the previous element, 
            // and it's not the first element of this recursion level, skip it.
            if (i > start && nums[i] == nums[i - 1]) {
                continue; 
            }

            // Choose: Append the element to our current path
            currentPath.add(nums[i]);

            // Explore: Move deeper into the tree, starting from the next index
            backtrack(i + 1, nums, currentPath, result);

            // Un-choose: Backtrack by removing the element to reset the state
            currentPath.remove(currentPath.size() - 1);
        }
    }
}