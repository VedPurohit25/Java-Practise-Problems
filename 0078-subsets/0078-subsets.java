import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void generateSubsets(int index, int[] nums, List<Integer> currentPath, List<List<Integer>> result) {
        // Base Case: If the decision pointer reaches the end, record the subset configuration
        if (index == nums.length) {
            result.add(new ArrayList<>(currentPath)); // Deep copy the active list structure
            return;
        }

        // Branch 1: Exclude the current element and move down the decision tree
        generateSubsets(index + 1, nums, currentPath, result);

        // Branch 2: Include the current element
        currentPath.add(nums[index]); // Choose
        generateSubsets(index + 1, nums, currentPath, result); // Explore
        
        // Backtrack: Remove the element to reset the state for alternative branches
        currentPath.remove(currentPath.size() - 1); // Un-choose
    }
}