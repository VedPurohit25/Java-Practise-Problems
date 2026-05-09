import java.util.ArrayList;
import java.util.List;

class Solution {
    public String convert(String s, int numRows) {
        // Base case: If 1 row or string is too short, no zigzag is possible
        if (numRows == 1 || s.length() <= numRows) return s;

        // Create a list of StringBuilders, one for each row
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;

        // Traverse the string
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            
            // If we are at the top or bottom, flip direction
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            
            // Move up or down based on the flag
            curRow += goingDown ? 1 : -1;
        }

        // Combine all rows into one final string
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        
        return res.toString();
    }
}