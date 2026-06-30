import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        
        // Base case: 0-bit gray code sequence is just [0]
        result.add(0);
        
        // Iteratively build the sequence from 1-bit up to n-bits
        for (int i = 0; i < n; i++) {
            // The bit shift representation of 1 << i corresponds to 2^i
            int mask = 1 << i;
            
            // Loop backward through the existing list to create the mirrored reflection
            for (int j = result.size() - 1; j >= 0; j--) {
                // Apply the bitmask to add a leading 1 to the reflected elements
                result.add(result.get(j) | mask);
            }
        }
        
        return result;
    }
}