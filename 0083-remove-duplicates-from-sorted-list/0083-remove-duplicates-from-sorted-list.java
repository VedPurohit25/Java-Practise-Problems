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
    public ListNode deleteDuplicates(ListNode head) {
        // Base Case: If the list is empty or contains only one node, no duplicates exist
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head;

        // Traverse the list until we reach the last node
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                // Duplicate detected: bypass the next node by linking directly to the one after it
                current.next = current.next.next;
            } else {
                // Unique value: safely advance the pointer forward
                current = current.next;
            }
        }

        return head;
    }
}