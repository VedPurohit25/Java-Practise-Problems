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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        // Dummy node acts as the foundational anchor before the modified head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode groupPrev = dummy;

        while (true) {
            // Step 1: Verify if a complete batch of size k exists ahead
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) {
                break; // Not enough nodes left; leave them untouched
            }
            
            // Track the start of the next group before we break connections
            ListNode groupNext = kth.next;

            // Step 2: Reverse the current batch of k nodes
            ListNode prev = kth.next; // Point the first node's next to the next group
            ListNode curr = groupPrev.next;
            
            while (curr != groupNext) {
                ListNode nextTmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTmp;
            }

            // Step 3: Weld the reversed group back into the main pipeline
            ListNode tmp = groupPrev.next; // The old head is now the new tail of the group
            groupPrev.next = kth;          // Connect previous group to the new head (kth)
            groupPrev = tmp;               // Shift groupPrev forward to the tail of our reversed group
        }

        return dummy.next;
    }

    // Helper method to look ahead and verify if k nodes exist
    private ListNode getKthNode(ListNode start, int k) {
        while (start != null && k > 0) {
            start = start.next;
            k--;
        }
        return start;
    }
}