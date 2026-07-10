public class Solution {
    public int countPrimes(int n) {
        // Base edge cases: there are no primes strictly less than 2 or 3
        if (n <= 2) return 0;
        if (n == 3) return 1; // Only 2

        // We assume all odd numbers are prime initially.
        // isNotPrime[i] will represent the odd number: (2 * i + 1)
        // Size needed is n / 2
        boolean[] isNotPrime = new boolean[n / 2];
        
        // Start count at 1 because we automatically include 2 (the only even prime)
        int count = 1; 
        
        int limit = (int) Math.sqrt(n);
        
        // Loop through odd numbers starting from 3
        // i represents the number: 2 * i + 1
        for (int i = 1; 2 * i + 1 <= limit; i++) {
            if (!isNotPrime[i]) {
                int prime = 2 * i + 1;
                
                // Mark multiples of this prime starting from prime * prime
                // The increment step is 2 * prime to hop over even multiples
                for (int j = prime * prime; j < n; j += 2 * prime) {
                    isNotPrime[j / 2] = true;
                }
            }
        }
        
        // Count how many odd numbers remain marked as prime
        // We start from index 1 (number 3) up to the max possible odd index less than n
        for (int i = 1; 2 * i + 1 < n; i++) {
            if (!isNotPrime[i]) {
                count++;
            }
        }
        
        return count;
    }
}