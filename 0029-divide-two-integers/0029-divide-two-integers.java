class Solution {
    public int divide(int dividend, int divisor) {
        // Handle the unique 32-bit signed integer overflow edge case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine if the final quotient should be negative
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        // Convert to absolute values using 64-bit longs to eliminate overflow errors
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        long quotient = 0;

        // Single-Pass Sweep: Scan downward from the maximum 32-bit position
        for (int i = 31; i >= 0; i--) {
            // Check if the divisor shifted left by 'i' fits inside the remaining dividend
            if ((absDivisor << i) <= absDividend) {
                // Subtract the shifted chunk from our remaining dividend
                absDividend -= (absDivisor << i);
                
                // Set the i-th bit of the quotient to 1
                quotient |= (1L << i);
            }
        }

        // Apply the correct structural sign flag
        long finalResult = isNegative ? -quotient : quotient;

        // Clamp within the strict 32-bit signed integer bounds
        if (finalResult > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (finalResult < Integer.MIN_VALUE) return Integer.MIN_VALUE;

        return (int) finalResult;
    }
}