   
     import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        // Derive the total number of nodes directly from the online array length
        int n = online.length;

        // Collect all unique edge costs to form our binary search space boundaries
        int[] uniqueCosts = new int[edges.length];
        for (int i = 0; i < edges.length; i++) {
            uniqueCosts[i] = edges[i][2];
        }
        Arrays.sort(uniqueCosts);

        // Build the adjacency list representation of the graph
        List<List<int[]>> adj = new ArrayList<>(n);
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            // Prune edges touching offline intermediate nodes immediately
            if (online[u] && online[v]) {
                adj.get(u).add(new int[]{v, cost});
                inDegree[v]++;
            }
        }

        // Generate standard Topological Sort order for the DAG
        int[] topoOrder = getTopologicalSort(n, adj, inDegree);

        // Binary search for the maximum valid bottleneck edge cost
        int low = 0;
        int high = uniqueCosts.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int targetBottleneck = uniqueCosts[mid];

            if (isValidPathPossible(n, adj, topoOrder, targetBottleneck, k)) {
                result = targetBottleneck; // Candidate found, attempt to maximize
                low = mid + 1;
            } else {
                high = mid - 1; // Constraint failed, decrease bottleneck requirement
            }
        }

        return result;
    }

    private boolean isValidPathPossible(int n, List<List<int[]>> adj, int[] topoOrder, int minAllowedCost, long k) {
        // dist[i] stores the minimum path cost from node 0 to node i
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        // Process nodes in topological order to compute the shortest path costs
        for (int u : topoOrder) {
            if (dist[u] == Long.MAX_VALUE) continue;

            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int edgeCost = neighbor[1];

                // Enforce the bottleneck filter constraint
                if (edgeCost >= minAllowedCost) {
                    if (dist[u] + edgeCost < dist[v]) {
                        dist[v] = dist[u] + edgeCost;
                    }
                }
            }
        }

        return dist[n - 1] <= k;
    }

    private int[] getTopologicalSort(int n, List<List<int[]>> adj, int[] inDegree) {
        int[] order = new int[n];
        int idx = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order[idx++] = curr;

            for (int[] neighbor : adj.get(curr)) {
                int v = neighbor[0];
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return order;
    }
}