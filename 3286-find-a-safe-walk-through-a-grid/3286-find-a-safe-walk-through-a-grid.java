import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        // dist[i][j] stores the minimum number of unsafe cells encountered to reach (i, j)
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        Deque<int[]> deque = new ArrayDeque<>();
        
        // Initialize the starting position
        int startCost = grid.get(0).get(0);
        dist[0][0] = startCost;
        deque.offerFirst(new int[]{0, 0});

        // Direction vectors for moving: Up, Down, Left, Right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int x = curr[0];
            int y = curr[1];

            // Early exit if we reached the lower-right destination
            if (x == m - 1 && y == n - 1) {
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // Ensure boundaries are preserved
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int cost = grid.get(nx).get(ny);
                    
                    // Relaxation Step: Check if we found a path with fewer unsafe cells
                    if (dist[x][y] + cost < dist[nx][ny]) {
                        dist[nx][ny] = dist[x][y] + cost;
                        
                        // 0-1 BFS optimization based on step cost
                        if (cost == 0) {
                            deque.offerFirst(new int[]{nx, ny});
                        } else {
                            deque.offerLast(new int[]{nx, ny});
                        }
                    }
                }
            }
        }

        // We can reach the end safely if the minimum health lost is strictly less than initial health
        return dist[m - 1][n - 1] < health;
    }
}