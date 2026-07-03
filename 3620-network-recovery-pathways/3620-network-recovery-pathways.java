   import java.util.Arrays;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        int m = edges.length;

        // 1. Extract and sort unique costs for Binary Search boundaries
        int[] uniqueCosts = new int[m];
        for (int i = 0; i < m; i++) {
            uniqueCosts[i] = edges[i][2];
        }
        Arrays.sort(uniqueCosts);

        // 2. High-Performance Forward Star Graph Representation (Zero Objects)
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] to = new int[m];
        int[] next = new int[m];
        int[] weight = new int[m];
        int[] inDegree = new int[n];
        
        int edgeCount = 0;
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int cost = edges[i][2];
            
            // Prune offline nodes immediately during construction
            if (online[u] && online[v]) {
                to[edgeCount] = v;
                weight[edgeCount] = cost;
                next[edgeCount] = head[u];
                head[u] = edgeCount;
                edgeCount++;
                inDegree[v]++;
            }
        }

        // 3. Flat Array Topological Sort (Array-Backed Queue)
        int[] topoOrder = new int[n];
        int[] queue = new int[n];
        int headPtr = 0;
        int tailPtr = 0;

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue[tailPtr++] = i;
            }
        }

        int topoIdx = 0;
        while (headPtr < tailPtr) {
            int curr = queue[headPtr++];
            topoOrder[topoIdx++] = curr;

            for (int e = head[curr]; e != -1; e = next[e]) {
                int v = to[e];
                if (--inDegree[v] == 0) {
                    queue[tailPtr++] = v;
                }
            }
        }

        // 4. Optimized Binary Search using reused primitive array
        long[] dist = new long[n];
        int low = 0;
        int high = uniqueCosts.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int targetBottleneck = uniqueCosts[mid];

            // Inlined, object-free verification pass
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[0] = 0;

            for (int i = 0; i < topoIdx; i++) {
                int u = topoOrder[i];
                if (dist[u] == Long.MAX_VALUE) continue;

                for (int e = head[u]; e != -1; e = next[e]) {
                    int edgeCost = weight[e];
                    if (edgeCost >= targetBottleneck) {
                        int v = to[e];
                        if (dist[u] + edgeCost < dist[v]) {
                            dist[v] = dist[u] + edgeCost;
                        }
                    }
                }
            }

            if (dist[n - 1] <= k) {
                result = targetBottleneck; 
                low = mid + 1; // Maximize bottleneck
            } else {
                high = mid - 1; // Relax constraint
            }
        }

        return result;
    }
}
   