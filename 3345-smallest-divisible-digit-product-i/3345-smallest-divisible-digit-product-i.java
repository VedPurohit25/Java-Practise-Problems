class Solution {
    public int smallestNumber(int n, int t) {
        int x = n;
        
        // Linear scan starting from n
        while (true) {
            if (getDigitProduct(x) % t == 0) {
                return x;
            }
            x++;
        }
    }
    
    // Helper method to compute the product of digits of a given number
    private int getDigitProduct(int num) {
        int product = 1;
        while (num > 0) {
            product *= (num % 10);
            num /= 10;
        }
        return product;
    }
}