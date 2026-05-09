class Solution {
    public boolean isPalindrome(int x) {
        // Step 1: Filter out impossible cases
        // Negative numbers are not palindromes.
        // If the last digit is 0, the first digit must be 0 (only possible for 0 itself).
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        // Step 2: Reverse only the second half of the number
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // Step 3: Check for symmetry
        // For even-length numbers (1221), x will be 12 and revertedNumber 12.
        // For odd-length numbers (121), x will be 1 and revertedNumber 12.
        // We can ignore the middle digit by doing revertedNumber / 10.
        return x == revertedNumber || x == revertedNumber / 10;
    }
}