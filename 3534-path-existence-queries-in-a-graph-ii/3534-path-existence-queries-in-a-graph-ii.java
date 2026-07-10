import java.util.*;

public class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Pair each value with its original index and sort by value
        int[][] sorted = new int[n][2];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = nums[i];
            sorted[i][1] = i;
        }
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Map original node indices to their positions in the sorted array
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[sorted[i][1]] = i;
        }
        
        // Step 2: Initialize binary lifting table
        // st[i][j] stores the farthest sorted index reachable from index i in 2^j jumps
        int LOG = 18; 
        int[][] st = new int[n][LOG];
        
        // Two-pointer approach to find the farthest 1-hop reach for each element
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (r < i) r = i;
            while (r + 1 < n && sorted[r + 1][0] - sorted[i][0] <= maxDiff) {
                r++;
            }
            st[i][0] = r;
        }
        
        // Build the sparse table for larger powers of 2 jumps
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                st[i][j] = st[st[i][j - 1]][j - 1];
            }
        }
        
        // Step 3: Answer each query using binary lifting
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];
            
            int a = pos[u];
            int b = pos[v];
            
            if (a == b) {
                ans[q] = 0;
                continue;
            }
            
            // Ensure we are always jumping from a smaller index to a larger index
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            
            int curr = a;
            int steps = 0;
            
            // Make greedy jumps using powers of 2 as long as we don't reach/overshoot b
            for (int j = LOG - 1; j >= 0; j--) {
                if (st[curr][j] < b && st[curr][j] > curr) {
                    curr = st[curr][j];
                    steps += (1 << j);
                }
            }
            
            // If one final hop can reach or overshoot b, the path exists
            if (st[curr][0] >= b) {
                ans[q] = steps + 1;
            } else {
                ans[q] = -1;
            }
        }
        
        return ans;
    }
}