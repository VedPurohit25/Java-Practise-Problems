import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxFreq = 0;

        // Step 1: Build the frequency map and find the max frequency
        for (int num : nums) {
            int currentFreq = freqMap.getOrDefault(num, 0) + 1;
            freqMap.put(num, currentFreq);
            maxFreq = Math.max(maxFreq, currentFreq);
        }

        // Step 2: Sum up frequencies that match the max
        int totalFrequency = 0;
        for (int f : freqMap.values()) {
            if (f == maxFreq) {
                totalFrequency += f;
            }
        }

        return totalFrequency;
    }
}