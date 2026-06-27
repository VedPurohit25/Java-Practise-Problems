import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();

        // Perform a single pass through the entire 9x9 matrix
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char val = board[r][c];

                // Only evaluate explicitly filled cells
                if (val != '.') {
                    // Encode unique string identifiers for all three constraints
                    String rowKey = val + " in row " + r;
                    String colKey = val + " in col " + c;
                    String boxKey = val + " in box " + (r / 3) + "-" + (c / 3);

                    // HashSet.add() returns false if the element is already present
                    if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                        return false; // Constraint violation detected
                    }
                }
            }
        }

        // All filled elements comply perfectly with structural laws
        return true;
    }
}