public class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder();
        
        while (columnNumber > 0) {
            // Shift to 0-indexed system
            columnNumber--; 
            
            // Get the remainder which maps to a character
            int remainder = columnNumber % 26;
            char ch = (char) ('A' + remainder);
            
            // Append character (it builds from right-to-left)
            result.append(ch);
            
            // Move to the next place value
            columnNumber /= 26;
        }
        
        // Since we built the string from right to left, reverse it at the end
        return result.reverse().toString();
    }
}