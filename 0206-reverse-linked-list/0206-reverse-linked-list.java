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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null){
            // 1. bookmark the next node
            ListNode nextTemp = curr.next;
            // 2. flip the pointer backwards 
            curr.next = prev;
            // 3. move prev and curr one step forward
            prev = curr;
            curr = nextTemp;
        }
        //prev will be sitting at the new head of the reversed list
        return prev;
    }
}