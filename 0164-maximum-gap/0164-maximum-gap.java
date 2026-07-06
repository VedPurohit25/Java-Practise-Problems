import java.util.Arrays;

public class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Step 1: Find the global min and max
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // If all elements are identical, the max gap is 0
        if (min == max) {
            return 0;
        }

        // Step 2: Calculate bucket size and count
        // We use Math.ceil((max - min) / (n - 1)) via integer arithmetic
        int bucketSize = (int) Math.ceil((double) (max - min) / (n - 1));
        int bucketCount = (max - min) / bucketSize + 1;

        // Arrays to store the min and max values for each bucket
        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // Step 3: Populate the buckets
        for (int num : nums) {
            int bucketIdx = (num - min) / bucketSize;
            bucketMin[bucketIdx] = Math.min(bucketMin[bucketIdx], num);
            bucketMax[bucketIdx] = Math.max(bucketMax[bucketIdx], num);
        }

        // Step 4: Calculate the maximum gap between sequential buckets
        int maxGap = 0;
        int previousMax = min; // Start tracking from global min

        for (int i = 0; i < bucketCount; i++) {
            // Skip empty buckets
            if (bucketMin[i] == Integer.MAX_VALUE) {
                continue;
            }

            // Gap is the difference between current bucket's min and previous bucket's max
            maxGap = Math.max(maxGap, bucketMin[i] - previousMax);
            previousMax = bucketMax[i];
        }

        return maxGap;
    }
}