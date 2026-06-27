import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Step 1: Sort to bring duplicates together and enable early pruning
        Arrays.sort(candidates);
        
        // Step 2: Launch the tracking engine
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentCombination, 
                           int[] candidates, int remainingTarget, int start) {
        // Base Case 1: Target matched perfectly
        if (remainingTarget == 0) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }

        // Iterate through the sorted candidates starting from the current index
        for (int i = start; i < candidates.length; i++) {
            // Early Pruning: If the current number exceeds the remaining target, 
            // no subsequent numbers can match because the array is sorted.
            if (candidates[i] > remainingTarget) {
                break;
            }

            // CRITICAL DUPLICATE PRUNING: Skip identical elements at the same recursion level
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Choice: Include the current candidate element
            currentCombination.add(candidates[i]);
            
            // Move to 'i + 1' to ensure this exact element cannot be reused in its own branch
            backtrack(result, currentCombination, candidates, remainingTarget - candidates[i], i + 1);
            
            // Backtrack: Clear the choice before transitioning to the next loop iteration
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}