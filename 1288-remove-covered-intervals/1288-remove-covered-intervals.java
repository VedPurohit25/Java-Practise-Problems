import java.util.Arrays;

public class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Step 1: Sort intervals
        // Ascending by start point; descending by end point if starts are equal
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        
        int count = 0;
        int maxEnd = 0;
        
        // Step 2: Traverse and find covered intervals
        for (int[] curr : intervals) {
            // If the current interval's end is covered by the maxEnd we've seen,
            // it means it is fully enclosed by a previous interval.
            if (curr[1] <= maxEnd) {
                count++;
            } else {
                // Otherwise, update the boundary
                maxEnd = curr[1];
            }
        }
        
        // Remaining intervals = Total intervals - Covered intervals
        return intervals.length - count;
    }
}