class Solution {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            // 1. Skip non-alphanumeric characters from the left
            if (!isAlphanumeric(leftChar)) {
                left++;
                continue;
            }

            // 2. Skip non-alphanumeric characters from the right
            if (!isAlphanumeric(rightChar)) {
                right--;
                continue;
            }

            // 3. Normalize to lowercase on the fly and compare
            if (toLowerCase(leftChar) != toLowerCase(rightChar)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // High-performance primitive character check (no wrapper methods)
    private boolean isAlphanumeric(char c) {
        return (c >= 'a' && c <= 'z') || 
               (c >= 'A' && c <= 'Z') || 
               (c >= '0' && c <= '9');
    }

    // Inlined lowercase normalization using simple bitwise/arithmetic logic
    private char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c + 32); // Shift ASCII boundary to lowercase
        }
        return c;
    }
}