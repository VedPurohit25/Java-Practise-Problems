class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        int currentSum = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            currentSum += nums[right];

            // While the current window meets the target, try to shrink it
            while (currentSum >= target) {
                // Update the minimum length found so far
                minLength = Math.min(minLength, right - left + 1);
                
                // Subtract the leftmost element and move the left border
                currentSum -= nums[left];
                left++;
            }
        }

        // If minLength was never updated, no such subarray exists
        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }
}