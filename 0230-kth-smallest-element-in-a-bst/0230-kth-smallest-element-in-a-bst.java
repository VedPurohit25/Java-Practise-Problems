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
import java.util.Stack;


class Solution {
    public int kthSmallest(TreeNode root, int k) {
        // We use a Stack to simulate the recursion of an Inorder Traversal
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        int count = 0;

        // The loop continues until we've visited all nodes or found our 'k'
        while (curr != null || !stack.isEmpty()) {
            // 1. Reach the leftmost node of the current subtree
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // 2. Process the node (this is the 'Inorder' part)
            curr = stack.pop();
            count++;

            // 3. Check if this is the k-th smallest value
            if (count == k) {
                return curr.val; // Success: Return the integer value
            }

            // 4. Move to the right subtree to continue the sorted sequence
            curr = curr.right;
        }

        // According to constraints, 1 <= k <= n, so we should always find a value.
        // We return a sentinel value just in case of unexpected input.
        return -1; 
    }
}