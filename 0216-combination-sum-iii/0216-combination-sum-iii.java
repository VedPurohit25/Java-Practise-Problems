import java.util.*;

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<>();
        backtrack(k, n, 1, new ArrayList<>(), results);
        return results;
    }

    private void backtrack(int remainingK, int remainingSum, int start, 
                           List<Integer> currentCombination, List<List<Integer>> results) {
        // Base case: if we found the exact number of elements and the exact sum
        if (remainingSum == 0 && remainingK == 0) {
            results.add(new ArrayList<>(currentCombination));
            return;
        }

        // Pruning: if we exceed elements or the sum becomes negative
        if (remainingSum < 0 || remainingK < 0) {
            return;
        }

        // Try numbers from 'start' up to 9
        for (int i = start; i <= 9; i++) {
            // Include the current number
            currentCombination.add(i);
            
            // Recurse with the next number (i + 1) to prevent using the same element twice
            backtrack(remainingK - 1, remainingSum - i, i + 1, currentCombination, results);
            
            // Backtrack: remove the last element to try other options
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}