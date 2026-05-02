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
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        // Use a queue to store pairs of nodes to be compared
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            // If both are null, this pair is symmetric; move to the next
            if (t1 == null && t2 == null) continue;
            
            // If one is null or values don't match, it's not symmetric
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;

            // Add children in mirror order:
            // 1. Outer nodes: t1.left and t2.right
            queue.add(t1.left);
            queue.add(t2.right);
            
            // 2. Inner nodes: t1.right and t2.left
            queue.add(t1.right);
            queue.add(t2.left);
        }

        return true;
    }
}