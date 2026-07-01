import java.util.LinkedList;
import java.util.Queue;

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
    public int minDepth(TreeNode root) {
        // Base Case: An empty tree has a structural depth of 0
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        // The root node itself represents the first tier of depth
        int depth = 1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Scan all nodes residing within the current horizontal level
            for (int i = 0; i < levelSize; i++) {
                TreeNode curr = queue.poll();

                // Early Termination Condition: First true leaf node encountered
                if (curr.left == null && curr.right == null) {
                    return depth;
                }

                // Append child links to populate the next tier down
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
            
            // Advance the depth marker as we transition to the lower tier
            depth++;
        }

        return depth;
    }
}