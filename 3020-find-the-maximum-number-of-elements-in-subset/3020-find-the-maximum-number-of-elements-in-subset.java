import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums) {
        // Step 1: Build a frequency map of all elements
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int maxLength = 1; // Any single element forms a valid sequence of length 1

        // Step 2: Handle the special edge case for 1
        if (countMap.containsKey(1)) {
            int onesCount = countMap.get(1);
            // Must be an odd length
            int validOnes = (onesCount % 2 == 0) ? onesCount - 1 : onesCount;
            maxLength = Math.max(maxLength, validOnes);
        }

        // Step 3: Climb the power mountain for each unique base > 1
        for (int base : countMap.keySet()) {
            if (base == 1) continue;

            int currentLength = 0;
            long currentPower = base; // Use long to prevent integer overflow during squaring

            // Keep climbing as long as the current element has at least 2 copies
            while (countMap.containsKey((int) currentPower) && countMap.get((int) currentPower) >= 2) {
                currentLength += 2;
                currentPower = currentPower * currentPower;
                
                // Safety break if the power exceeds the upper limit of the constraint
                if (currentPower > 1000000000) break;
            }

            // Check if the next power can serve as the single peak element
            if (currentPower <= 1000000000 && countMap.containsKey((int) currentPower)) {
                currentLength += 1; // Found an exact peak
            } else {
                currentLength -= 1; // Backtrack: the last element must become the peak instead
            }

            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }
}