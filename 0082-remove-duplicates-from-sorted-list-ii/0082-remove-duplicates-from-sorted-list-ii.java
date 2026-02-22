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
    public ListNode deleteDuplicates(ListNode head) {
        // Dummy node helps handle cases where the head is deleted
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (head != null) {
            // If it's a start of duplicates range
            if (head.next != null && head.val == head.next.val) {
                // Move until the end of duplicates range
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // Skip all duplicates
                prev.next = head.next;
            } else {
                // Otherwise, move prev
                prev = prev.next;
            }
            // Move head for the next iteration
            head = head.next;
        }

        return dummy.next;
    }
}