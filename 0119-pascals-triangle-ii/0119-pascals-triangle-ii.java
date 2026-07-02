import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        
        // Base value: The first element C(n, 0) is inherently locked to 1
        long currentTerm = 1;
        row.add((int) currentTerm);

        // Calculate subsequent elements sequentially up to the target rowIndex boundary
        for (int k = 1; k <= rowIndex; k++) {
            // Apply the reduced combination recurrence formula: C(n, k) = C(n, k-1) * (n - k + 1) / k
            currentTerm = currentTerm * (rowIndex - k + 1) / k;
            
            row.add((int) currentTerm);
        }

        return row;
    }
}