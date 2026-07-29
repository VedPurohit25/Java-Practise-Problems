import java.util.Arrays;

class Solution {
    private static final long MAX = 1000005; // Cap to avoid overflow since k <= 10^6

    public String smallestPalindrome(String s, long k) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int oddCount = 0;
        char midLetter = '\n';
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midLetter = (char) ('a' + i);
            }
            halfCount[i] = count[i] / 2;
        }

        if (oddCount > 1) {
            return "";
        }

        long totalPerm = countArrangements(halfCount);
        if (k > totalPerm) {
            return "";
        }

        StringBuilder leftHalf = new StringBuilder();
        int halfLen = s.length() / 2;

        for (int step = 0; step < halfLen; step++) {
            for (int i = 0; i < 26; i++) {
                if (halfCount[i] == 0) continue;

                halfCount[i]--;
                long arrangements = countArrangements(halfCount);

                if (arrangements >= k) {
                    leftHalf.append((char) ('a' + i));
                    break;
                } else {
                    k -= arrangements;
                    halfCount[i]++;
                }
            }
        }

        String leftStr = leftHalf.toString();
        String rightStr = new StringBuilder(leftStr).reverse().toString();

        return oddCount == 1 ? leftStr + midLetter + rightStr : leftStr + rightStr;
    }

    private long countArrangements(int[] count) {
        int total = 0;
        for (int c : count) total += c;

        long res = 1;
        for (int c : count) {
            if (c == 0) continue;
            res = multiply(res, nCk(total, c));
            if (res >= MAX) return MAX;
            total -= c;
        }
        return res;
    }

    private long nCk(int n, int k) {
        long res = 1;
        k = Math.min(k, n - k);
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
            if (res >= MAX) return MAX;
        }
        return res;
    }

    private long multiply(long a, long b) {
        if (a == 0 || b == 0) return 0;
        if (MAX / a < b) return MAX;
        return Math.min(MAX, a * b);
    }
}