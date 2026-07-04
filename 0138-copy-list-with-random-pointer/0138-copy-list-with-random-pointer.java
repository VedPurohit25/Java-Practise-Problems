/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        // Step 1: Clone and Interweave
        Node curr = head;
        while (curr != null) {
            Node nextNode = curr.next;
            Node copy = new Node(curr.val);
            curr.next = copy;
            copy.next = nextNode;
            curr = nextNode;
        }
        
        // Step 2: Assign Random Pointers
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                // The copy's random points to the original's random's copy
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next; // Move to the next original node
        }
        
        // Step 3: Separate the Lists
        curr = head;
        Node dummyHead = new Node(0);
        Node copyCurr = dummyHead;
        
        while (curr != null) {
            Node nextOriginal = curr.next.next;
            
            // Extract the copy
            copyCurr.next = curr.next;
            copyCurr = copyCurr.next;
            
            // Restore the original link
            curr.next = nextOriginal;
            
            curr = nextOriginal;
        }
        
        return dummyHead.next;
    }
}