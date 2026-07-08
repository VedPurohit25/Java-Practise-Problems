import java.util.Arrays;

public class Solution {
    private static final int MOD = 1_000_000_007;

    // Renamed from handleQueries to sumAndMultiply to match the driver code
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        int q = queries.length;

        // Precompute powers of 10 and their modular inverses
        long[] pow10 = new long[m + 1];
        long[] invPow10 = new long[m + 1];
        pow10[0] = 1;
        invPow10[0] = 1;
        
        long inv10 = power(10, MOD - 2); // Modular inverse of 10 modulo 10^9+7

        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
            invPow10[i] = (invPow10[i - 1] * inv10) % MOD;
        }

        // prefixNonZeroCount[i] = number of non-zero digits in s[0...i-1]
        int[] prefixNonZeroCount = new int[m + 1];
        // prefixSumOfDigits[i] = sum of digits in s[0...i-1]
        int[] prefixSumOfDigits = new int[m + 1];
        // prefixX[i] = sum of (digit * 10^-count) for s[0...i-1]
        long[] prefixX = new long[m + 1];

        for (int i = 0; i < m; i++) {
            int digit = s.charAt(i) - '0';
            
            prefixNonZeroCount[i + 1] = prefixNonZeroCount[i];
            prefixSumOfDigits[i + 1] = prefixSumOfDigits[i] + digit;
            prefixX[i + 1] = prefixX[i];

            if (digit != 0) {
                prefixNonZeroCount[i + 1]++;
                // Contribution scaled down by 10^(current non-zero count)
                long contribution = (digit * invPow10[prefixNonZeroCount[i + 1]]) % MOD;
                prefixX[i + 1] = (prefixX[i + 1] + contribution) % MOD;
            }
        }

        int[] answer = new int[q];

        for (int i = 0; i < q; i++) {
            int L = queries[i][0];
            int R = queries[i][1];

            // 1. Calculate sum of digits in range [L, R]
            long sum = prefixSumOfDigits[R + 1] - prefixSumOfDigits[L];

            // 2. Calculate the value of x formed by non-zero digits
            long rawX = (prefixX[R + 1] - prefixX[L] + MOD) % MOD;
            int totalNonZeroUpToR = prefixNonZeroCount[R + 1];
            
            // Shift the scaled down value back up using 10^(total non-zero count up to R)
            long x = (rawX * pow10[totalNonZeroUpToR]) % MOD;

            // 3. Compute final answer for this query
            long queryAns = (x * sum) % MOD;
            answer[i] = (int) queryAns;
        }

        return answer;
    }

    // Fast exponentiation helper to calculate (base^exp) % MOD
    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }
}