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
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return buildUniqueBSTs(1, n);
    }

    private List<TreeNode> buildUniqueBSTs(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();
        
        // Base Case: No values left to allocate. Return a list containing null.
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // Step 1: Iterate through the range to make each number 'i' the root
        for (int i = start; i <= end; i++) {
            
            // Step 2: Recursively compile all valid left sub-trees using elements less than i
            List<TreeNode> leftSubTrees = buildUniqueBSTs(start, i - 1);
            
            // Step 3: Recursively compile all valid right sub-trees using elements greater than i
            List<TreeNode> rightSubTrees = buildUniqueBSTs(i + 1, end);

            // Step 4: Combine every left tree with every right tree under the current root i
            for (TreeNode left : leftSubTrees) {
                for (TreeNode right : rightSubTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    
                    // Commit the fully assembled structural configuration to the list
                    allTrees.add(root);
                }
            }
        }

        return allTrees;
    }
}