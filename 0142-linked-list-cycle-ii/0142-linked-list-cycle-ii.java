/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        
        // Phase 1: Cycle Detection
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        
        // If there is no cycle, return null
        if (!hasCycle) {
            return null;
        }
        
        // Phase 2: Find the entry point of the cycle
        slow = head; // Reset slow to head
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next; // Move fast at the same speed now
        }
        
        return slow; // Both meet at the start of the cycle
    }
}