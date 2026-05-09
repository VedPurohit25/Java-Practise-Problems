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
    public ListNode rotateRight(ListNode head, int k) {
        // Edge case: empty list, single node, or no rotation needed
        if (head == null || head.next == null || k == 0) return head;

        // 1. Calculate length and find the tail
        ListNode tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // 2. Connect tail to head to make it circular
        tail.next = head;

        // 3. Find the new tail: (length - k % length - 1) steps from head
        k = k % length;
        int stepsToNewTail = length - k;
        
        ListNode newTail = tail; 
        while (stepsToNewTail-- > 0) {
            newTail = newTail.next;
        }

        // 4. Set the new head and break the circular connection
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}