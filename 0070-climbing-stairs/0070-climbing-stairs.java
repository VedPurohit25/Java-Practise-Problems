class Solution {
    public int climbStairs(int n) {
        // Base Cases: 1 way for 1 step, 2 ways for 2 steps
        if (n <= 2) return n;

        // Trackers for the last two sequential stair heights
        int prev2 = 1; // Represents ways(i - 2)
        int prev1 = 2; // Represents ways(i - 1)
        int current = 0;

        // Sequentially compute combination totals from step 3 up to n
        for (int i = 3; i <= n; i++) {
            current = prev1 + prev2; // The core recurrence addition
            prev2 = prev1;           // Shift lower window boundary forward
            prev1 = current;         // Shift upper window boundary forward
        }

        return current;
    }
}