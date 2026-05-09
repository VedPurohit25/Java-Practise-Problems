class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isGood(int num) {
        boolean hasChanged = false;
        while (num > 0) {
            int digit = num % 10;
            
            // If it contains 3, 4, or 7, it's immediately invalid
            if (digit == 3 || digit == 4 || digit == 7) return false;
            
            // If it contains 2, 5, 6, or 9, it will definitely be different
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                hasChanged = true;
            }
            
            num /= 10;
        }
        // Must be valid AND different from original
        return hasChanged;
    }
}