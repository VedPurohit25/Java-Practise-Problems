import java.util.*;

class Solution {
    // Helper class to store exponents of prime factors 2, 3, and 5
    record State(int p2, int p3, int p5) {}

    public int countSequences(int[] nums, long k) {
        // Break target k into its prime factors
        State targetState = getFactors(k);
        if (targetState == null && k != 1) return 0; // k has factors other than 2, 3, 5

        Map<State, Long> dp = new HashMap<>();
        dp.put(new State(0, 0, 0), 1L);

        for (int num : nums) {
            Map<State, Long> nextDp = new HashMap<>();
            State numFactors = getFactors(num);

            for (Map.Entry<State, Long> entry : dp.entrySet()) {
                State s = entry.getKey();
                long count = entry.getValue();

                // 1. Multiply: Add exponents
                add(nextDp, new State(s.p2 + numFactors.p2, s.p3 + numFactors.p3, s.p5 + numFactors.p5), count);
                
                // 2. Divide: Subtract exponents
                add(nextDp, new State(s.p2 - numFactors.p2, s.p3 - numFactors.p3, s.p5 - numFactors.p5), count);
                
                // 3. Stay: Keep exponents the same
                add(nextDp, s, count);
            }
            dp = nextDp;
        }

        State finalTarget = (k == 1) ? new State(0, 0, 0) : targetState;
        return (int) (long) dp.getOrDefault(finalTarget, 0L);
    }

    private void add(Map<State, Long> map, State s, long count) {
        map.put(s, map.getOrDefault(s, 0L) + count);
    }

    private State getFactors(long n) {
        if (n <= 0) return null;
        int p2 = 0, p3 = 0, p5 = 0;
        while (n > 0 && n % 2 == 0) { n /= 2; p2++; }
        while (n > 0 && n % 3 == 0) { n /= 3; p3++; }
        while (n > 0 && n % 5 == 0) { n /= 5; p5++; }
        return (n == 1) ? new State(p2, p3, p5) : null;
    }
}