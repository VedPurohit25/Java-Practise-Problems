public class Solution {
    public int trailingZeroes(int n) {
        int count = 0;
        
        // Divide n by 5, then 25, 125, etc.
        while (n >= 5) {
            count += n / 5;
            n /= 5; // Efficiently transitions to the next power of 5
        }
        
        return count;
    }
}