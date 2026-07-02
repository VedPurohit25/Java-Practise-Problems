import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> resultPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        
        // Execute the tracking engine
        backtrack(root, targetSum, currentPath, resultPaths);
        
        return resultPaths;
    }

    private void backtrack(TreeNode root, int remainingSum, List<Integer> currentPath, List<List<Integer>> resultPaths) {
        // Base Case 1: Empty reference bounds
        if (root == null) {
            return;
        }

        // Action: Record the node's value into our running ledger stack
        currentPath.add(root.val);
        remainingSum -= root.val;

        // Base Case 2: Hit a leaf node. If the sum matches perfectly, capture a copy of the path
        if (root.left == null && root.right == null) {
            if (remainingSum == 0) {
                resultPaths.add(new ArrayList<>(currentPath));
            }
        } else {
            // Recursive Exploration: Continue probing available lower paths
            backtrack(root.left, remainingSum, currentPath, resultPaths);
            backtrack(root.right, remainingSum, currentPath, resultPaths);
        }

        // Backtrack: Remove the current element from the ledger before returning up the call stack
        currentPath.remove(currentPath.size() - 1);
    }
}