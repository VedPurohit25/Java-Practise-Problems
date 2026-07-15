class Solution {
    public int gcdOfOddEvenSums(int n) {
        // Calculate the sum of the first n odd numbers: n^2
        int sumOdd = n * n;
        
        // Calculate the sum of the first n even numbers: n * (n + 1)
        int sumEven = n * (n + 1);
        
        // Compute the GCD using the Euclidean algorithm
        return gcd(sumOdd, sumEven);
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}