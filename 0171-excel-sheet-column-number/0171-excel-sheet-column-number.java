public class Solution {
    public int titleToNumber(String columnTitle) {
        int result = 0;
        int len = columnTitle.length();
        
        for (int i = 0; i < len; i++) {
            // Shift the current total to the left in base-26
            result *= 26;
            
            // Add the value of the current character (A = 1, B = 2, ...)
            result += (columnTitle.charAt(i) - 'A' + 1);
        }
        
        return result;
    }
}