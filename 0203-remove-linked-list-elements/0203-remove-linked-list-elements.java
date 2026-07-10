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
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // Create a dummy node that points to the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Use a pointer to traverse the list starting from the dummy node
        ListNode curr = dummy;
        
        while (curr.next != null) {
            if (curr.next.val == val) {
                // Skip the node that matches the target value
                curr.next = curr.next.next;
            } else {
                // Move to the next node only if we didn't delete anything
                curr = curr.next;
            }
        }
        
        // The dummy's next node is the new head of the modified list
        return dummy.next;
    }
}