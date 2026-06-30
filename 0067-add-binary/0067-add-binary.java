class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        
        // Initialize pointers at the least significant bits
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        // Process loops until both bitstreams are exhausted and no carry remains
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;

            // Extract bit from string a if within bounds
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }

            // Extract bit from string b if within bounds
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            // Append the binary digit for the current position (sum % 2)
            sb.append(sum % 2);

            // Compute the carry bit to propagate to the left (sum / 2)
            carry = sum / 2;
        }

        // Reverse the accumulated buffer to achieve correct reading order
        return sb.reverse().toString();
    }
}