import java.util.*;

public class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Build the adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;

        // Traverse all vertices to find all connected components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // Tracking variables for the current component
                int vertexCount = 0;
                int edgeCount = 0;

                // BFS to traverse the component
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                visited[i] = true;

                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    vertexCount++;
                    // Add the degree of the current vertex to the component's edge count
                    edgeCount += adj.get(curr).size();

                    for (int neighbor : adj.get(curr)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }

                // Since each undirected edge is counted twice (once for each endpoint),
                // the actual number of unique edges in the component is edgeCount / 2.
                // A component is complete if its unique edge count equals vertexCount * (vertexCount - 1) / 2.
                // This simplifies to comparing edgeCount directly with vertexCount * (vertexCount - 1).
                if (edgeCount == vertexCount * (vertexCount - 1)) {
                    completeComponentsCount++;
                }
            }
        }

        return completeComponentsCount;
    }
}