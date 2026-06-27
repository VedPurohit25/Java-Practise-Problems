class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;

        int left = 0;
        int right = 0;
        int maxLength = 0;

        // Pass 1: Left-to-Right Scan
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * right);
            } else if (right > left) {
                // Invalid state: excess closing brackets break the chain
                left = 0;
                right = 0;
            }
        }

        // Reset counters for the reverse sweep
        left = 0;
        right = 0;

        // Pass 2: Right-to-Left Scan (handles excess opening brackets like "(()")
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                maxLength = Math.max(maxLength, 2 * left);
            } else if (left > right) {
                // Invalid state: excess opening brackets break the chain from this direction
                left = 0;
                right = 0;
            }
        }

        return maxLength;
    }
}
