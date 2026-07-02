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
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        
        while (curr != null) {
            // Only proceed if a left subtree exists
            if (curr.left != null) {
                
                // 1. Find the rightmost node of the left subtree
                TreeNode rightmost = curr.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                
                // 2. Wire the right subtree to the end of the left subtree
                rightmost.right = curr.right;
                
                // 3. Move the left subtree to the right and nullify the left
                curr.right = curr.left;
                curr.left = null;
            }
            
            // Move to the next node in the newly formed right-leaning list
            curr = curr.right;
        }
    }
}