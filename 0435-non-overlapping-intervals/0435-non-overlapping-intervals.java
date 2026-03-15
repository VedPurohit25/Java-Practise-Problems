import java.util.*;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        // Sort by end time to leave as much room as possible for others
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0;
        int lastEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            // Check if the current interval starts before the last one ends
            if (intervals[i][0] < lastEnd) {
                count++; // Overlap detected, remove this one
            } else {
                // No overlap, update the lastEnd to the current interval's end
                lastEnd = intervals[i][1];
            }
        }

        return count;
    }
}