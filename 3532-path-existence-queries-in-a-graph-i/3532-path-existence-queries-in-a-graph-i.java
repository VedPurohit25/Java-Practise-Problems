import java.util.ArrayList;
import java.util.List;

public class Solution {
    // Renamed to pathExistenceQueries to match the driver script
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Track the component group id for each node
        int[] group = new int[n];
        int currentGroup = 0;
        group[0] = 0;
        
        for (int i = 1; i < n; i++) {
            // If the difference between consecutive elements exceeds maxDiff,
            // the continuous path is broken, starting a new group.
            if (nums[i] - nums[i - 1] > maxDiff) {
                currentGroup++;
            }
            group[i] = currentGroup;
        }
        
        // Step 2: Process each query and store in a primitive boolean array
        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            
            // If they belong to the same group, a path exists
            answer[i] = (group[u] == group[v]);
        }
        
        return answer;
    }
}