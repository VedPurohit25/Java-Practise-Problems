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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // Edge Case: If left matches right, no structural modifications are needed
        if (head == null || left == right) {
            return head;
        }

        // Setup a placeholder dummy node to gracefully insulate the true head pointer
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Step 1: Advance prev to sit exactly one step prior to the reversal zone
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // curr anchors the starting node of our target inversion window
        ListNode curr = prev.next;

        // Step 2: Loop to progressively flip references in place
        for (int i = 0; i < right - left; i++) {
            ListNode nextNodes = curr.next; // Temporary reference cache
            
            // Re-route the active pointer around nextNodes
            curr.next = nextNodes.next;
            
            // Splice nextNodes smoothly into its new position right after prev
            nextNodes.next = prev.next;
            prev.next = nextNodes;
        }

        // Return the true head, which remains insulated behind our dummy node
        return dummy.next;
    }
}