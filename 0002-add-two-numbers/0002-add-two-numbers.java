/**
 * Definition for singly-linked list.
 * public class ListNode {
      int val;
      ListNode next;
     ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }*/
 
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addRecursive(l1, l2, 0);
    }

    private ListNode addRecursive(ListNode l1, ListNode l2, int carry) {
        // Base case: No more digits and no carry to process
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        // Calculate current sum
        int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
        
        // Create current node
        ListNode result = new ListNode(sum % 10);
        
        // Move to the next digits
        ListNode nextL1 = (l1 != null) ? l1.next : null;
        ListNode nextL2 = (l2 != null) ? l2.next : null;
        
        // The "Next" of this node is the result of the next recursive call
        result.next = addRecursive(nextL1, nextL2, sum / 10);
        
        return result;
    }
}