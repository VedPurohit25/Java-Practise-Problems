class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // Traverse the array backward from the least significant digit
        for (int i = n - 1; i >= 0; i--) {
            // If the current digit is less than 9, increment and terminate
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // System boundary secured, early return optimization
            }
            
            // If the digit is 9, it rolls over to 0, and the carry cascades leftward
            digits[i] = 0;
        }

        // Boundary Overflow Case: If the loop finishes, it means the number was all 9s
        // Java initializes integer arrays with 0s by default, so we only write the leading 1
        int[] overflowResult = new int[n + 1];
        overflowResult[0] = 1;
        
        return overflowResult;
    }
}