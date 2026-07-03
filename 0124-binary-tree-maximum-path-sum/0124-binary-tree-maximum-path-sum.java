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
    public int maxPathSum(TreeNode root) {
        // Primitive wrapper array to hold our maximum path sum across calls
        int[] globalMax = new int[]{Integer.MIN_VALUE};
        calculateMaxGain(root, globalMax);
        return globalMax[0];
    }

    private int calculateMaxGain(TreeNode node, int[] globalMax) {
        if (node == null) {
            return 0;
        }

        // 1. Recursively compute the maximum contribution from left and right subtrees
        // Prune negative sums by floor-bounding them at 0
        int leftGain = Math.max(calculateMaxGain(node.left, globalMax), 0);
        int rightGain = Math.max(calculateMaxGain(node.right, globalMax), 0);

        // 2. Compute the price of a path turning at the current node
        int currentPathSum = node.val + leftGain + rightGain;

        // 3. Update our absolute maximum path tracker if this turn yields a higher score
        if (currentPathSum > globalMax[0]) {
            globalMax[0] = currentPathSum;
        }

        // 4. Return the maximum gain the parent node can obtain by choosing this node
        return node.val + Math.max(leftGain, rightGain);
    }
}