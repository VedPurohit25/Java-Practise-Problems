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
    int postIdx;
    Map<Integer, Integer> inMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIdx = postorder.length - 1;
        
        // Map inorder values to their indices
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        
        return construct(inorder, 0, inorder.length - 1, postorder);
    }

    private TreeNode construct(int[] inorder, int left, int right, int[] postorder) {
        if (left > right) return null;

        // Last element of current postorder segment is the root
        int rootVal = postorder[postIdx--];
        TreeNode root = new TreeNode(rootVal);

        int mid = inMap.get(rootVal);

        // ALWAYS build Right subtree first when using Postorder traversal
        root.right = construct(inorder, mid + 1, right, postorder);
        root.left = construct(inorder, left, mid - 1, postorder);

        return root;
    }
}