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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Condition 1: Both nodes are structurally null (Reached terminal leaves simultaneously)
        if (p == null && q == null) {
            return true;
        }
        
        // Condition 2: Structural Mismatch (One tree terminates early while the other continues)
        if (p == null || q == null) {
            return false;
        }
        
        // Condition 3: Value Mismatch (Structure matches, but data contents differ)
        if (p.val != q.val) {
            return false;
        }
        
        // Step deeper: Evaluate left child paths and right child paths concurrently
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}