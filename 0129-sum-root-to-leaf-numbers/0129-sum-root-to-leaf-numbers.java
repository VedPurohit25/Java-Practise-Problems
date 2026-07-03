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
    public int sumNumbers(TreeNode root) {
        // Start the recursive accumulation with a base path value of 0
        return calculatePathSum(root, 0);
    }
    
    private int calculatePathSum(TreeNode node, int currentSum) {
        // Base case: if the node is null, it contributes nothing to the sum
        if (node == null) {
            return 0;
        }
        
        // Update the running total for the current path
        currentSum = currentSum * 10 + node.val;
        
        // If we reach a leaf node, return the fully accumulated number
        if (node.left == null && node.right == null) {
            return currentSum;
        }
        
        // Otherwise, recurse down both the left and right subtrees and sum the results
        return calculatePathSum(node.left, currentSum) + calculatePathSum(node.right, currentSum);
    }
}