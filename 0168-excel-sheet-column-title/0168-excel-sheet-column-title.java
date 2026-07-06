public class Solution {
    public String convertToTitle(int columnNumber) {
        // Max characters for 2^31 - 1 in base 26 is 7
        char[] buffer = new char[7];
        int index = 7; // Start at the end of the array
        
        while (columnNumber > 0) {
            columnNumber--; // Shift to 0-indexed system
            
            // Fill the array backwards
            buffer[--index] = (char) ('A' + (columnNumber % 26));
            
            columnNumber /= 26;
        }
        
        // Construct the string directly from the populated slice of the array
        return new String(buffer, index, 7 - index);
    }
}