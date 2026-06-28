class Solution {
    private int solutionCount = 0;

    public int totalNQueens(int n) {
        solutionCount = 0; // Reset counter for execution isolation
        
        // O(1) space constraints tracking lookups
        boolean[] cols = new boolean[n];
        boolean[] mainDiagonals = new boolean[2 * n];
        boolean[] antiDiagonals = new boolean[2 * n];
        
        // Ignite the counting search tree at row 0
        backtrack(n, 0, cols, mainDiagonals, antiDiagonals);
        
        return solutionCount;
    }

    private void backtrack(int n, int row, boolean[] cols, 
                           boolean[] mainDiagonals, boolean[] antiDiagonals) {
        // Base Case: A valid solution has been reached
        if (row == n) {
            solutionCount++;
            return;
        }

        // Evaluate putting a queen in each available column slot of the current row
        for (int col = 0; col < n; col++) {
            int mainDiagIdx = row - col + n;
            int antiDiagIdx = row + col;

            // Prune branch immediately if any layout constraint is violated
            if (cols[col] || mainDiagonals[mainDiagIdx] || antiDiagonals[antiDiagIdx]) {
                continue;
            }

            // Choice: Lock constraints for this column/diagonal intersection
            cols[col] = true;
            mainDiagonals[mainDiagIdx] = true;
            antiDiagonals[antiDiagIdx] = true;

            // Step deeper into the next row level
            backtrack(n, row + 1, cols, mainDiagonals, antiDiagonals);

            // Backtrack: Release constraints to let sibling iterations test this space
            cols[col] = false;
            mainDiagonals[mainDiagIdx] = false;
            antiDiagonals[antiDiagIdx] = false;
        }
    }
}