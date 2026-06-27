import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Initiate the recursive backtracking engine starting from index 0
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentCombination, 
                           int[] candidates, int remainingTarget, int start) {
        // Base Case 1: Target achieved perfectly
        if (remainingTarget == 0) {
            result.add(new ArrayList<>(currentCombination)); // Create a deep copy of the configuration
            return;
        }
        
        // Base Case 2: Target overshot or bounds exhausted
        if (remainingTarget < 0 || start == candidates.length) {
            return;
        }

        // Choice 1: Include the current candidate number (index remains 'start' to allow reuse)
        currentCombination.add(candidates[start]);
        backtrack(result, currentCombination, candidates, remainingTarget - candidates[start], start);
        
        // Backtrack: Undo the choice before moving to the next branch
        currentCombination.remove(currentCombination.size() - 1);

        // Choice 2: Skip the current candidate entirely and move forward to the next index
        backtrack(result, currentCombination, candidates, remainingTarget, start + 1);
    }
}
