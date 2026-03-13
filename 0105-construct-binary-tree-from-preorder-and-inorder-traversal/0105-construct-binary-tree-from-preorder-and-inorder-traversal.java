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
    int preIdx = 0;
    Map<Integer, Integer> inMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Map inorder values to indices for O(1) lookup
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return arrayToTree(preorder, 0, inorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        if (left > right) return null;

        // The first element in preorder is the root
        int rootVal = preorder[preIdx++];
        TreeNode root = new TreeNode(rootVal);

        // Split inorder array into left and right subtrees
        int mid = inMap.get(rootVal);

        // Recursively build the subtrees
        root.left = arrayToTree(preorder, left, mid - 1);
        root.right = arrayToTree(preorder, mid + 1, right);

        return root;
    }
}