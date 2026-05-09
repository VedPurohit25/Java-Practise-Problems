class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length;
        int n = box[0].length;

        // Step 1: Apply gravity row by row (stones move to the right)
        for (int i = 0; i < m; i++) {
            int emptySpot = n - 1; // The rightmost potential landing spot
            for (int j = n - 1; j >= 0; j--) {
                if (box[i][j] == '*') {
                    // Obstacle resets the landing spot to just before it
                    emptySpot = j - 1;
                } else if (box[i][j] == '#') {
                    // Stone falls to the current emptySpot
                    box[i][j] = '.';
                    box[i][emptySpot] = '#';
                    emptySpot--;
                }
            }
        }

        // Step 2: Rotate the box 90 degrees clockwise
        char[][] rotatedBox = new char[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // (r, c) -> (c, M - 1 - r)
                rotatedBox[j][m - 1 - i] = box[i][j];
            }
        }

        return rotatedBox;
    }
}