class Solution {
    public String longestCommonPrefix(String[] strs) {
        // Edge case: empty array
        if (strs == null || strs.length == 0) return "";
        
        // Initialize the prefix with the first structural blueprint
        String prefix = strs[0];
        
        // Scan horizontally across the rest of the strings
        for (int i = 1; i < strs.length; i++) {
            // Trim the prefix until strs[i] starts with it
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                
                // If the foundation shrinks to nothing, break early
                if (prefix.isEmpty()) return "";
            }
        }
        
        return prefix;
    }
}