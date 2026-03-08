import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. Frequency Array (Offset by 10000 to handle range -10000 to 10000)
        // This replaces the slow HashMap
        int[] freq = new int[20001];
        for (int n : nums) {
            freq[n + 10000]++;
        }

        // 2. Min-Heap of int[] {number, frequency}
        // Comparing frequency directly inside the heap is faster
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                pq.add(new int[]{i - 10000, freq[i]});
                // Keep the heap size at exactly K
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }

        // 3. Extract results
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll()[0];
        }
        
        return result;
    }
}