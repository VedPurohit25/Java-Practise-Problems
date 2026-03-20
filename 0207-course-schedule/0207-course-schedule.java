import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        
        // Build the graph
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int dependency = pre[1];
            adj.get(dependency).add(course);
            inDegree[course]++;
        }
        
        // Add courses with no prerequisites to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            count++;
            
            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        return count == numCourses;
    }
}