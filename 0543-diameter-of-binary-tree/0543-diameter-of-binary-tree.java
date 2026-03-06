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
    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeight(root);
        return maxDiameter;
    }

    private int calculateHeight(TreeNode node) {
        if (node == null) return 0;

        // Recursive calls to find the height of subtrees
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        // Update the global diameter if the path through this node is longer
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        // Return the height of this node to the parent
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
        
   