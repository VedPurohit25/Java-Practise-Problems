import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 1. Build frequency map - O(N)
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        // 2. Create buckets where index = frequency - O(N)
        // Max frequency is nums.length
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        // 3. Collect the top k elements - O(N)
        int[] result = new int[k];
        int counter = 0;
        for (int pos = bucket.length - 1; pos >= 0 && counter < k; pos--) {
            if (bucket[pos] != null) {
                for (int num : bucket[pos]) {
                    result[counter++] = num;
                    if (counter == k) break;
                }
            }
        }
        return result;
    }
}