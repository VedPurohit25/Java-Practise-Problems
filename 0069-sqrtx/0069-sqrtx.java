class Solution {
    public int mySqrt(int x) {
        // Base Case Handling for 0 and 1
        if (x < 2) return x;

        int left = 1;
        int right = x;
        int ans = 0;

        while (left <= right) {
            // Safe midpoint calculation preventing integer overflow
            int mid = left + (right - left) / 2;

            // Equivalent to: mid * mid <= x
            if (mid <= x / mid) {
                ans = mid;       // Cache the valid rounded-down candidate
                left = mid + 1;  // Look for a larger potential square root value
            } else {
                right = mid - 1; // Overshot the target; shrink the upper bound
            }
        }

        return ans;
    }
}