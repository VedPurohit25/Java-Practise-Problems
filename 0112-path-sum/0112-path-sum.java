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
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // Base Case 1: An empty tree or branch path has no elements to form a sum
        if (root == null) {
            return false;
        }

        // Substract the current node's value from the running target budget
        targetSum -= root.val;

        // Base Case 2: If we hit a terminal leaf node, check if the budget is perfectly cleared
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }

        // Explore deeper: Return true if either the left path OR the right path satisfies the target
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }
}