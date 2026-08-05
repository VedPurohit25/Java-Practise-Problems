import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // 1. Build Adjacency List for the method invocation graph
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] inv : invocations) {
            adj[inv[0]].add(inv[1]);
        }
        
        // 2. Identify all suspicious methods starting from method 'k' via BFS
        boolean[] isSuspicious = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(k);
        isSuspicious[k] = true;
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : adj[curr]) {
                if (!isSuspicious[neighbor]) {
                    isSuspicious[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        
        // 3. Verify if any non-suspicious method invokes a suspicious method
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!isSuspicious[u] && isSuspicious[v]) {
                // Suspicious group cannot be isolated; return all methods
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }
        
        // 4. Collect and return remaining non-suspicious methods
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                result.add(i);
            }
        }
        
        return result;
    }
}