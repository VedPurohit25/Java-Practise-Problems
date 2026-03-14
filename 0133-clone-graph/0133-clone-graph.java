/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

import java.util.*;

class Solution {
    // Map to keep track of original nodes and their clones
    private HashMap<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // If the node was already cloned, return the clone
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a new node (The Clone)
        Node cloneNode = new Node(node.val, new ArrayList<>());
        
        // Map the original to the clone before recursing (to handle cycles)
        visited.put(node, cloneNode);

        // Recursively clone and connect all neighbors
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }
}