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
import java.util.*;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // Min-Heap based on node value
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of each non-empty list
        for (ListNode node : lists) {
            if (node != null) pq.add(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode smallest = pq.poll();
            tail.next = smallest;
            tail = tail.next;

            // If this list has more nodes, add the next one to the heap
            if (smallest.next != null) {
                pq.add(smallest.next);
            }
        }

        return dummy.next;
    }
}