class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Base Cases
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        // Recursive Step
        if (list1.val <= list2.val) {
            // list1 is smaller, so it's the head. 
            // We find its 'next' by recursing with the rest of list1.
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            // list2 is smaller, so it's the head.
            // We find its 'next' by recursing with the rest of list2.
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}