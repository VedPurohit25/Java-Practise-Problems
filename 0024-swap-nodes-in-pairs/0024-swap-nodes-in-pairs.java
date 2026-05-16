/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        // Base case: If the list is empty or has only one node, no swaps are possible
        if (head == null || head.next == null) {
            return head;
        }

        // Initialize a dummy node to act as the stable structural anchor
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode prev = dummy;

        // Traverse while there is a complete pair remaining to swap
        while (prev.next != null && prev.next.next != null) {
            ListNode firstNode = prev.next;
            ListNode secondNode = prev.next.next;

            // Step 1: Rewire the entry point to the pair
            prev.next = secondNode;

            // Step 2: Route the first node's pointer past the second node
            firstNode.next = secondNode.next;

            // Step 3: Route the second node back to the first node to complete the swap
            secondNode.next = firstNode;

            // Step 4: Advance the anchor pointer forward to prepare for the next iteration
            prev = firstNode;
        }

        // Return the true restructured head of the linked list
        return dummy.next;
    }
}