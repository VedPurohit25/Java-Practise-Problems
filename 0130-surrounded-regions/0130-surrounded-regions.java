class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        
        int rows = board.length;
        int cols = board[0].length;
        
        // 1. Mark Safe Zones from the boundaries
        // Left and Right boundaries
        for (int r = 0; r < rows; r++) {
            dfs(board, r, 0);
            dfs(board, r, cols - 1);
        }
        // Top and Bottom boundaries
        for (int c = 0; c < cols; c++) {
            dfs(board, 0, c);
            dfs(board, rows - 1, c);
        }
        
        // 2 & 3. Process the entire board
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X'; // Capture isolated O
                } else if (board[r][c] == 'S') {
                    board[r][c] = 'O'; // Restore safe O
                }
            }
        }
    }
    
    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != 'O') {
            return;
        }
        
        board[r][c] = 'S'; // Temporary mark for "Safe"
        
        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }
}