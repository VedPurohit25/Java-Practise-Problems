class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Target identified
            if (nums[mid] == target) {
                return mid;
            }

            // Step 1: Check if the left half is monotonically sorted
            if (nums[left] <= nums[mid]) {
                // Step 2a: Verify if the target sits within the left boundaries
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1; // Narrow search to the left half
                } else {
                    left = mid + 1;  // Pivot right
                }
            } 
            // Step 1b: Otherwise, the right half must be monotonically sorted
            else {
                // Step 2b: Verify if the target sits within the right boundaries
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;  // Narrow search to the right half
                } else {
                    right = mid - 1; // Pivot left
                }
            }
        }

        // Target not found within the structural limits
        return -1;
    }
}