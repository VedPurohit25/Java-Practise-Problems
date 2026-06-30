import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int n, int k, List<Integer> currentPath, List<List<Integer>> result) {
        // Base Case: If the active subset reaches size k, capture it
        if (currentPath.size() == k) {
            result.add(new ArrayList<>(currentPath)); // Deep copy the current path configuration
            return;
        }

        // Optimization: Prune branches where there aren't enough remaining elements to reach size k
        int upperBound = n - (k - currentPath.size()) + 1;

        for (int i = start; i <= upperBound; i++) {
            // 1. Choose: Append the active integer to the path
            currentPath.add(i);

            // 2. Explore: Move deeper into the decision tree, forcing strict increasing order (i + 1)
            backtrack(i + 1, n, k, currentPath, result);

            // 3. Un-choose: Backtrack by removing the last element to explore alternative paths
            currentPath.remove(currentPath.size() - 1);
        }
    }
}