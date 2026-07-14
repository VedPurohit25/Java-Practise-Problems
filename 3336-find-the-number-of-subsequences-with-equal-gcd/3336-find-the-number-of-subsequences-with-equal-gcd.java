class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // dp[g1][g2] represents the count of disjoint subsequence pairs 
        // with GCD of seq1 = g1 and GCD of seq2 = g2.
        // Index 0 represents an empty subsequence.
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1; // Base case: both subsequences are empty

        for (int num : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;

                    long count = dp[g1][g2];

                    // Option 1: Do not include 'num' in either subsequence
                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + count) % MOD);

                    // Option 2: Include 'num' in seq1
                    int nextG1 = (g1 == 0) ? num : gcd(g1, num);
                    nextDp[nextG1][g2] = (int) ((nextDp[nextG1][g2] + count) % MOD);

                    // Option 3: Include 'num' in seq2
                    int nextG2 = (g2 == 0) ? num : gcd(g2, num);
                    nextDp[g1][nextG2] = (int) ((nextDp[g1][nextG2] + count) % MOD);
                }
            }
            dp = nextDp;
        }

        // Accumulate all valid states where g1 == g2 (excluding empty subsequences where g = 0)
        long totalPairs = 0;
        for (int g = 1; g <= maxVal; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }

        return (int) totalPairs;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}