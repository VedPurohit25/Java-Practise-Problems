class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int f0 = 0;

        // Step 1: Calculate Total Sum and initial F(0)
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f0 += i * nums[i];
        }

        int maxVal = f0;
        int currentF = f0;

        // Step 2: Use the recurrence relation to find F(1)...F(n-1)
        // F(k) = F(k-1) + sum - n * nums[n - k]
        for (int k = 1; k < n; k++) {
            // The element that was at the end moves to the front (coefficient becomes 0)
            // Every other element's coefficient increases by 1
            currentF = currentF + sum - n * nums[n - k];
            maxVal = Math.max(maxVal, currentF);
        }

        return maxVal;
    }
}