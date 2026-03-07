/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // Global variables to track state during recursion
    // 'prev' stores the value of the node visited just before the current one
    private Integer prev = null;
    // 'minDiff' keeps track of the smallest difference found so far
    private int minDiff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        // Step 1: Start the In-order Traversal
        performInOrder(root);
        
        // Step 4: Return the result after the entire tree is traversed
        return minDiff;
    }

    private void performInOrder(TreeNode node) {
        // Base Case: If the node is null, we've reached a leaf's child
        if (node == null) {
            return;
        }

        // Step 2 (Left): Recurse down the left subtree
        performInOrder(node.left);

        // Step 3 (Root): Process the current node
        // In a BST, the in-order traversal gives values in sorted order.
        // The minimum difference must be between two adjacent values in this order.
        if (prev != null) {
            // Update minDiff if the current gap is smaller than previous ones
            minDiff = Math.min(minDiff, node.val - prev);
        }
        
        // Update 'prev' to the current node's value before moving right
        prev = node.val;

        // Step 2 (Right): Recurse down the right subtree
        performInOrder(node.right);
    }
}