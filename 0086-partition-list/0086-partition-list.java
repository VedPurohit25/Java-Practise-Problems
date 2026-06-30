/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        // Instantiate placeholder dummy nodes to stabilize list initialization
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);
        
        // Pointers to track the moving trailing edges of both sub-lists
        ListNode lessTail = lessDummy;
        ListNode greaterTail = greaterDummy;
        
        ListNode current = head;
        
        // Step 1: Route nodes into their respective lanes based on threshold x
        while (current != null) {
            if (current.val < x) {
                lessTail.next = current;       // Connect node to the less-than list
                lessTail = lessTail.next;     // Move the tail tracker forward
            } else {
                greaterTail.next = current;    // Connect node to the greater-than-or-equal list
                greaterTail = greaterTail.next; // Move the tail tracker forward
            }
            current = current.next;            // Advance down the original list
        }
        
        // Step 2: Cut off potential cycles by terminating the greater list's tail reference
        greaterTail.next = null;
        
        // Step 3: Link the less-than tail directly to the head of the greater-than sublist
        lessTail.next = greaterDummy.next;
        
        // Return the beginning of the re-woven less-than chain
        return lessDummy.next;
    }
}