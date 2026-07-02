import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>(numRows);

        // Loop to generate each individual row up to the target number
        for (int i = 0; i < numRows; i++) {
            List<Integer> currentRow = new ArrayList<>(i + 1);
            
            // Build out elements within the current horizontal tier
            for (int j = 0; j <= i; j++) {
                // Outer Boundaries are strictly locked to 1
                if (j == 0 || j == i) {
                    currentRow.add(1);
                } else {
                    // Extract values from the preceding historical row layout
                    List<Integer> prevRow = triangle.get(i - 1);
                    int combinedValue = prevRow.get(j - 1) + prevRow.get(j);
                    currentRow.add(combinedValue);
                }
            }
            
            // Commit the complete row state to the master triangle container
            triangle.add(currentRow);
        }

        return triangle;
    }
}