import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        // Step 1: Build the adjacency list
        // Each node will map to a list of pairs (neighbor, distance)
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int wt = road[2];
            adj.get(u).add(new int[]{v, wt});
            adj.get(v).add(new int[]{u, wt}); // Bidirectional
        }
        
        // Step 2: BFS initialization
        int minScore = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.offer(1);
        visited[1] = true;
        
        // Step 3: Traverse the connected component
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            
            for (int[] neighbor : adj.get(curr)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];
                
                // Track the absolute minimum edge weight seen in this component
                minScore = Math.min(minScore, weight);
                
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }
        
        return minScore;
    }
}