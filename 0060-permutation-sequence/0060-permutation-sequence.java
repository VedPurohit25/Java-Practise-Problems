import java.util.ArrayList;
import java.util.List;

class Solution {
    public String getPermutation(int n, int k) {
        // Step 1: Precompute factorial values up to n and build the available numbers pool
        int[] factorials = new int[n + 1];
        factorials[0] = 1;
        List<Integer> numbers = new ArrayList<>();
        
        for (int i = 1; i <= n; i++) {
            factorials[i] = factorials[i - 1] * i;
            numbers.add(i); // Pool of unused numbers: [1, 2, 3, ..., n]
        }

        // Convert k to a 0-indexed system to match list offsets
        k--;

        StringBuilder sb = new StringBuilder();

        // Step 2: Determine each digit sequentially from left to right
        for (int i = n - 1; i >= 0; i--) {
            // Calculate the size of each permutation sub-block at the current position
            int blockSize = factorials[i];
            
            // Identify which sub-block index contains our target k
            int targetIndex = k / blockSize;
            
            // Retrieve the number corresponding to that index and append it
            sb.append(numbers.get(targetIndex));
            
            // Remove the selected number from the pool so it cannot be reused
            numbers.remove(targetIndex);
            
            // Update k to locate its relative offset within the newly selected sub-block
            k %= blockSize;
        }

        return sb.toString();
    }
}