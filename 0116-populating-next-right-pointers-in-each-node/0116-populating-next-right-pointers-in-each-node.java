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
        
        // Start with the root node as the horizon marker
        Node levelStart = root;
        
        // Loop down the levels as long as a child level exists to connect
        while (levelStart.left != null) {
            Node curr = levelStart;
            
            // Traverse horizontally across the current completed level
            while (curr != null) {
                // Connection 1: Join left child to right child
                curr.left.next = curr.right;
                
                // Connection 2: Bridge right child to next parent's left child
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                
                // Move to the next parent node on the horizontal chain
                curr = curr.next;
            }
            
            // Move down to the first node of the newly connected level
            levelStart = levelStart.left;
        }
        
        return root;
    }
}