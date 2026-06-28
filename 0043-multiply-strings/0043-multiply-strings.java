class Solution {
    public String multiply(String num1, String num2) {
        // Edge Case: If either multiplier is zero, the product is zero
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int len1 = num1.length();
        int len2 = num2.length();
        // The product of an n-digit and m-digit number can have at most n + m digits
        int[] result = new int[len1 + len2];

        // Step 1: Build the multiplication grid in reverse order
        for (int i = len1 - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';
            
            for (int j = len2 - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';
                int product = digit1 * digit2;

                // Position calculation matching index offsets
                int p1 = i + j;
                int p2 = i + j + 1;

                // Add current product to the existing buffer at the unit spot
                int sum = product + result[p2];

                // Distribute between the unit position and the carry position
                result[p2] = sum % 10;
                result[p1] += sum / 10;
            }
        }

        // Step 2: Convert the integer array back into a readable String
        StringBuilder sb = new StringBuilder();
        for (int digit : result) {
            // Skip leading zeros that weren't utilized by carries
            if (!(sb.length() == 0 && digit == 0)) {
                sb.append(digit);
            }
        }

        return sb.toString();
    }
}