class Solution {
    public double myPow(double x, int n) {
        // Convert to a 64-bit long to safely handle Integer.MIN_VALUE overflow
        long N = n;
        
        // Handle negative exponent transformation: x^(-N) = (1/x)^N
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double result = 1.0;
        double currentProduct = x;

        // Iterative Bitwise Exponentiation Pass
        while (N > 0) {
            // If the lowest bit of N is 1 (N is odd), multiply the current accumulated product
            if ((N & 1) == 1) {
                result *= currentProduct;
            }
            
            // Square the base component
            currentProduct *= currentProduct;
            
            // Shift N right by 1 bit (equivalent to N /= 2)
            N >>= 1;
        }

        return result;
    }
}