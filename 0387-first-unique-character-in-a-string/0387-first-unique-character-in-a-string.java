import java.util.*;

class Solution {
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 'a']++;
            queue.add(i);
            
            // Remove characters from the front that are no longer unique
            while (!queue.isEmpty() && count[s.charAt(queue.peek()) - 'a'] > 1) {
                queue.poll();
            }
        }
        
        return queue.isEmpty() ? -1 : queue.peek();
    }
}