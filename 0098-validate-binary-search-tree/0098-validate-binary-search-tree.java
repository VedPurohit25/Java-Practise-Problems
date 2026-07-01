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
    public boolean isValidBST(TreeNode root) {
        // Instantiate the validation loop using Long values to safely envelope the Integer boundaries
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long low, long high) {
        // Base Case: An empty leaf path is inherently a valid BST structure
        if (node == null) {
            return true;
        }

        // Integrity Check: Verify if the current node value breaks the boundary limits
        if (node.val <= low || node.val >= high) {
            return false;
        }

        // Explore Left: Update the upper bound to the current node's value
        // Explore Right: Update the lower bound to the current node's value
        return validate(node.left, low, node.val) && validate(node.right, node.val, high);
    }
}