class Solution {
    public String reverseWords(String s) {
        // Convert to char array to simulate mutability
        char[] a = s.toCharArray();
        int n = a.length;
        
        // 1. Reverse the entire string
        reverse(a, 0, n - 1);
        
        // 2. Reverse each word back to normal order
        reverseWordsInArray(a, n);
        
        // 3. Clean up inner and outer whitespace shifts
        return cleanSpaces(a, n);
    }
    
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }
    
    private void reverseWordsInArray(char[] a, int n) {
        int i = 0, j = 0;
        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++; // skip spaces
            while (j < i || j < n && a[j] != ' ') j++; // skip non-spaces
            reverse(a, i, j - 1);                      // reverse individual word
        }
    }
    
    private String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;
        while (j < n) {
            while (j < n && a[j] == ' ') j++;             // skip spaces
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non-spaces
            while (j < n && a[j] == ' ') j++;             // skip spaces
            if (j < n) a[i++] = ' ';                      // keep only one space if more words exist
        }
        return new String(a, 0, i);
    }
}