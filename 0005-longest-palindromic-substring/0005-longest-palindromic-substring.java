class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0, end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd length (center is at i)
            int len1 = expandAroundCenter(s, i, i);
            // Case 2: Even length (center is between i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Take the maximum of both expansion types
            int len = Math.max(len1, len2);
            
            // If we found a longer palindrome, update our boundaries
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        // Expand as long as the characters match and we are within bounds
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return the length of the palindrome found
        return right - left - 1;
    }
}