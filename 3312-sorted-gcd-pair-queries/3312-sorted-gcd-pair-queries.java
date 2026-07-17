import java.util.Arrays;

public class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // Step 1: Find the maximum element in nums to define the sieve's boundary
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // Count frequency of each number in the input array
        int[] counts = new int[maxVal + 1];
        for (int num : nums) {
            counts[num]++;
        }

        // Step 2: For each integer i, count how many elements in nums are multiples of i
        long[] divisibleCount = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            for (int j = i; j <= maxVal; j += i) {
                divisibleCount[i] += counts[j];
            }
        }

        // Step 3: Compute the exact number of pairs whose greatest common divisor is exactly g
        long[] gcdCount = new long[maxVal + 1];
        for (int g = maxVal; g >= 1; g--) {
            long c = divisibleCount[g];
            // Total pairs among numbers divisible by g
            long totalPairs = c * (c - 1) / 2;

            // Subtract pairs where the actual GCD is a strictly larger multiple (2g, 3g, etc.)
            for (int mult = 2 * g; mult <= maxVal; mult += g) {
                totalPairs -= gcdCount[mult];
            }
            gcdCount[g] = totalPairs;
        }

        // Step 4: Construct a prefix sum array of GCD counts
        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + gcdCount[i];
        }

        // Step 5: Resolve each query using binary search (equivalent to bisect_right)
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            
            // Binary search for the smallest GCD value whose prefix sum is strictly greater than q
            int low = 1, high = maxVal;
            int targetGcd = maxVal;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSums[mid] > q) {
                    targetGcd = mid; // Candidate GCD found, try to find a smaller one
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            answer[i] = targetGcd;
        }

        return answer;
    }
}