public class Solution {
    public String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        
        // Step 1: Create the combined string for KMP processing
        String reversedS = new StringBuilder(s).reverse().toString();
        String temp = s + "#" + reversedS;
        
        // Step 2: Compute the LPS (Longest Prefix Suffix) array
        int n = temp.length();
        int[] lps = new int[n];
        
        // KMP table construction logic
        for (int i = 1; i < n; i++) {
            int j = lps[i - 1];
            
            while (j > 0 && temp.charAt(i) != temp.charAt(j)) {
                j = lps[j - 1];
            }
            
            if (temp.charAt(i) == temp.charAt(j)) {
                j++;
            }
            lps[i] = j;
        }
        
        // Step 3: The last element of LPS array gives the length of the longest palindromic prefix
        int longestPalindromicPrefixLen = lps[n - 1];
        
        // Step 4: Get the non-palindromic suffix, reverse it, and prepend it
        String suffixToPrepend = s.substring(longestPalindromicPrefixLen);
        String reversedSuffix = new StringBuilder(suffixToPrepend).reverse().toString();
        
        return reversedSuffix + s;
    }
}