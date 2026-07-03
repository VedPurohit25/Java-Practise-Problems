import java.util.Arrays;

class Solution {
    public int minCut(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        
        int n = s.length();
        // cuts[i] will store the minimum cuts needed for the substring s[0...i]
        int[] cuts = new int[n];
        
        // Initialize the array with the maximum possible cuts.
        // For a string of length i + 1, the maximum cuts needed is i (cutting every single character).
        for (int i = 0; i < n; i++) {
            cuts[i] = i;
        }
        
        // Loop through every possible center of a palindrome
        for (int mid = 0; mid < n; mid++) {
            // Case 1: Odd-length palindromes centered at 'mid' (e.g., "aba")
            findPalindromes(s, mid, mid, cuts);
            
            // Case 2: Even-length palindromes centered between 'mid' and 'mid + 1' (e.g., "abba")
            findPalindromes(s, mid, mid + 1, cuts);
        }
        
        return cuts[n - 1];
    }
    
    private void findPalindromes(String s, int left, int right, int[] cuts) {
        int n = s.length();
        
        // Expand outward as long as the characters match, forming a valid palindrome s[left...right]
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            if (left == 0) {
                // If the palindrome starts from the absolute beginning of the string, 
                // then the substring s[0...right] is a palindrome. Zero cuts needed!
                cuts[right] = 0;
            } else {
                // Otherwise, the cuts needed is (minimum cuts for the prefix before this palindrome) + 1
                cuts[right] = Math.min(cuts[right], cuts[left - 1] + 1);
            }
            
            // Move pointers outward
            left--;
            right++;
        }
    }
}