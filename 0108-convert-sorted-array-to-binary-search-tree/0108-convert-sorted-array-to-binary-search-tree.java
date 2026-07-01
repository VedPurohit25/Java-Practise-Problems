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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return constructBST(nums, 0, nums.length - 1);
    }

    private TreeNode constructBST(int[] nums, int start, int end) {
        // Base Case: Boundary crosses, no array elements remain for allocation
        if (start > end) {
            return null;
        }

        // Calculate the absolute midpoint to preserve height balance
        int mid = start + (end - start) / 2;
        
        // Form the local root node around the median element
        TreeNode root = new TreeNode(nums[mid]);

        // Recursively build out the left and right balanced sub-trees
        root.left = constructBST(nums, start, mid - 1);
        root.right = constructBST(nums, mid + 1, end);

        return root;
    }
}