import java.util.*;

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        List<Integer> values = new ArrayList<>();

        // 1. Extract all values from list1
        while (list1 != null) {
            values.add(list1.val);
            list1 = list1.next;
        }

        // 2. Extract all values from list2
        while (list2 != null) {
            values.add(list2.val);
            list2 = list2.next;
        }

        // 3. Sort the collection
        Collections.sort(values);

        // 4. Rebuild a new linked list from the sorted values
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        for (int val : values) {
            current.next = new ListNode(val);
            current = current.next;
        }

        return dummy.next;
    }
}