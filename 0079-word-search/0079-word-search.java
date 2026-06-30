class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        // Linear scan to find the initial starting node candidate
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // If the first character matches, launch the DFS verification scout
                if (board[r][c] == word.charAt(0)) {
                    if (dfsScout(r, c, 0, board, word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfsScout(int r, int c, int wordIndex, char[][] board, String word) {
        // Base Case: If the entire word sequence has been matched successfully
        if (wordIndex == word.length()) {
            return true;
        }

        // Boundary and Character Match Validation Check
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(wordIndex)) {
            return false;
        }

        // Step 1: Choose - Cache the original letter and mask the cell to block reuse
        char originalChar = board[r][c];
        board[r][c] = '#'; 

        // Step 2: Explore - Fan out in all 4 cardinal directions sequentially
        boolean wordFound = dfsScout(r + 1, c, wordIndex + 1, board, word) || // Down
                            dfsScout(r - 1, c, wordIndex + 1, board, word) || // Up
                            dfsScout(r, c + 1, wordIndex + 1, board, word) || // Right
                            dfsScout(r, c - 1, wordIndex + 1, board, word);   // Left

        // Step 3: Un-choose - Restore the cell's state during the backtracking unwinding phase
        board[r][c] = originalChar;

        return wordFound;
    }
}