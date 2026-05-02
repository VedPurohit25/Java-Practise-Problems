class Solution {
    public int commonFactors(int a, int b) {
        int count = 0;
        // The common factor cannot exceed the smaller number
        int limit = Math.min(a, b);
        
        for (int i = 1; i <= limit; i++) {
            // Check if i divides both a and b perfectly
            if (a % i == 0 && b % i == 0) {
                count++;
            }
        }
        
        return count;
    }
}