/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        // The current parent node we are processing on the active horizon
        Node curr = root;

        while (curr != null) {
            // Virtual placeholder node to catch the start of the next level
            Node dummy = new Node(0);
            Node tail = dummy;

            // Horizontal scan across the current completed level
            while (curr != null) {
                // If a left child exists, stitch it onto the lower chain
                if (curr.left != null) {
                    tail.next = curr.left;
                    tail = tail.next;
                }
                // If a right child exists, stitch it onto the lower chain
                if (curr.right != null) {
                    tail.next = curr.right;
                    tail = tail.next;
                }
                
                // Advance to the adjacent parent node on the horizontal line
                curr = curr.next;
            }

            // Transition: Drop straight down to the first true child of the newly formed level
            curr = dummy.next;
        }

        return root;
    }
}