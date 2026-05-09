import java.util.*;

class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);

        // 1. Sieve of Eratosthenes to pre-calculate primes and SPF
        int[] spf = new int[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) spf[i] = i;
        for (int i = 2; i * i <= maxVal; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= maxVal; j += i) {
                    if (spf[j] == j) spf[j] = i;
                }
            }
        }

        // 2. Map prime factors to indices divisible by them
        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            // Factorize val to find unique prime factors
            Set<Integer> uniqueFactors = new HashSet<>();
            while (val > 1) {
                uniqueFactors.add(spf[val]);
                val /= spf[val];
            }
            for (int p : uniqueFactors) {
                primeToIndices.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
            }
        }

        // 3. BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;
        int jumps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int curr = queue.poll();
                if (curr == n - 1) return jumps;

                // Move Adjacent
                int[] nextIdxs = {curr - 1, curr + 1};
                for (int next : nextIdxs) {
                    if (next >= 0 && next < n && !visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }

                // Prime Teleportation (Only if current value is prime)
                int val = nums[curr];
                if (val >= 2 && spf[val] == val) { // Check if nums[curr] is prime
                    if (primeToIndices.containsKey(val)) {
                        for (int next : primeToIndices.get(val)) {
                            if (!visited[next]) {
                                visited[next] = true;
                                queue.offer(next);
                            }
                        }
                        // Crucial: Clear bucket after use to prevent O(N^2)
                        primeToIndices.remove(val);
                    }
                }
            }
            jumps++;
        }

        return -1;
    }
}
