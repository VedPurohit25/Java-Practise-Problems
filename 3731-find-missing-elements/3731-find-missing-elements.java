import java.util.*;

class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> presentSet = new HashSet<>();
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        // Find min, max, and add elements to the set for O(1) lookups
        for (int num : nums) {
            presentSet.add(num);
            if (num < min) min = num;
            if (num > max) max = num;
        }
        
        // Sweep through the complete range and collect missing integers
        for (int i = min; i <= max; i++) {
            if (!presentSet.contains(i)) {
                result.add(i);
            }
        }
        
        return result;
    }
}