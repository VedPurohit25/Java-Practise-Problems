public class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        // Edge cases for very small n
        if (n == 1) return 1; 
        if (n == 2) return 2; 
        
        // Find the number of bits needed to represent n
        int bits = 0;
        int temp = n;
        while (temp > 0) {
            bits++;
            temp >>= 1;
        }
        
        // The total number of unique values from 0 to (2^bits - 1) is 2^bits
        return 1 << bits;
    }
}