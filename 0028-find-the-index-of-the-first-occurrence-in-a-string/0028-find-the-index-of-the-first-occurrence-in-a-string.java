class Solution {
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length();
        int nLen = needle.length();
        
        // Edge case: if needle is longer than haystack, it cannot be a substring
        if (hLen < nLen) {
            return -1;
        }
        
        // Only loop up to the point where the remaining characters can fit the needle
        for (int i = 0; i <= hLen - nLen; i++) {
            // Optimization: check the first and last character of the window before substring matching
            if (haystack.charAt(i) == needle.charAt(0) && 
                haystack.charAt(i + nLen - 1) == needle.charAt(nLen - 1)) {
                
                // If boundary characters match, verify the full string slice
                if (haystack.substring(i, i + nLen).equals(needle)) {
                    return i;
                }
            }
        }
        
        return -1;
    }
}