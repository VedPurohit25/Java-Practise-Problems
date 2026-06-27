class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        // Scan the entire 9x9 matrix for an empty space
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == '.') {
                    
                    // Try placing digits 1-9
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, row, col, c)) {
                            board[row][col] = c; // Tentatively place character
                            
                            // Recursively proceed to solve the rest of the board
                            if (solve(board)) {
                                return true; // Solution found, propagate true up the call stack
                            }
                            
                            // Backtrack: Undo selection if it leads to a dead end
                            board[row][col] = '.';
                        }
                    }
                    
                    return false; // Triggers backtracking if no digit 1-9 works here
                }
            }
        }
        return true; // Returns true when there are no more empty cells left
    }

    // Comprehensive constraint validation helper
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // Rule 1: Check Row constraint
            if (board[row][i] == c) return false;
            
            // Rule 2: Check Column constraint
            if (board[i][col] == c) return false;
            
            // Rule 3: Check 3x3 Box constraint
            // Row index offsets: 3 * (row / 3) + i / 3
            // Col index offsets: 3 * (col / 3) + i % 3
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
        }
        return true;
    }
}