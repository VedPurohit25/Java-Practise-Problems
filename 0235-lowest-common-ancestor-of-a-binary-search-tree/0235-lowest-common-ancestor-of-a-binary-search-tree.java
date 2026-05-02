/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;

        while (current != null) {
            // If both nodes are in the left wing
            if (p.val < current.val && q.val < current.val) {
                current = current.left;
            } 
            // If both nodes are in the right wing
            else if (p.val > current.val && q.val > current.val) {
                current = current.right;
            } 
            // We found the split point!
            else {
                return current;
            }
        }
        return null;
    }
}