class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 1. Split
        ListNode prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;

        // 2. Sort
        ListNode leftSide = sortList(head);
        ListNode rightSide = sortList(slow);

        // 3. Merge (Renamed to match the Driver's expectation)
        return mergeTwoLists(leftSide, rightSide);
    }

    // Change 'merge' to 'mergeTwoLists' here
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        
        if (l1 != null) current.next = l1;
        if (l2 != null) current.next = l2;

        return dummy.next;
    }
}