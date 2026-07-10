public class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        
        // isPrime[i] will track if number i is prime
        boolean[] isPrime = new boolean[n];
        // Initialize all elements to true
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        
        // Loop up to the square root of n
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                // Mark all multiples of i as non-prime
                // Starting from i*i because smaller multiples have already been marked
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        // Count the remaining true values
        int primeCount = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                primeCount++;
            }
        }
        
        return primeCount;
    }
}