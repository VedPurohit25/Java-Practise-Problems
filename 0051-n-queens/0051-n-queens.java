import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        
        // Configuration state array representing the column position of the queen in each row
        int[] queens = new int[n];
        
        // O(1) Constraint tracking lookups
        boolean[] cols = new boolean[n];
        boolean[] mainDiagonals = new boolean[2 * n];
        boolean[] antiDiagonals = new boolean[2 * n];
        
        // Initiate the backtracking engine at row 0
        backtrack(result, queens, n, 0, cols, mainDiagonals, antiDiagonals);
        return result;
    }

    private void backtrack(List<List<String>> result, int[] queens, int n, int row,
                           boolean[] cols, boolean[] mainDiagonals, boolean[] antiDiagonals) {
        // Base Case: All rows successfully assigned without collision
        if (row == n) {
            result.add(buildBoardConfiguration(queens, n));
            return;
        }

        // Evaluate placing a queen in every column of the current row
        for (int col = 0; col < n; col++) {
            int mainDiagIdx = row - col + n;
            int antiDiagIdx = row + col;

            // Prune branch immediately if a constraint collision is detected
            if (cols[col] || mainDiagonals[mainDiagIdx] || antiDiagonals[antiDiagIdx]) {
                continue;
            }

            // Choice: Lock the queen placement into position
            queens[row] = col;
            cols[col] = true;
            mainDiagonals[mainDiagIdx] = true;
            antiDiagonals[antiDiagIdx] = true;

            // Advance recursively to solve the subsequent row layout
            backtrack(result, queens, n, row + 1, cols, mainDiagonals, antiDiagonals);

            // Backtrack: Undo all constraints to clear the field for the next peer iteration
            cols[col] = false;
            mainDiagonals[mainDiagIdx] = false;
            antiDiagonals[antiDiagIdx] = false;
        }
    }

    // Structural helper to convert integer placements into the required String grid format
    private List<String> buildBoardConfiguration(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] rowPattern = new char[n];
            java.util.Arrays.fill(rowPattern, '.');
            rowPattern[queens[i]] = 'Q'; // Set the queen position
            board.add(new String(rowPattern));
        }
        return board;
    }
}