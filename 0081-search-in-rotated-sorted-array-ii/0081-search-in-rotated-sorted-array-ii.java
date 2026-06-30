class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Target located successfully
            if (nums[mid] == target) {
                return true;
            }

            // Edge Case: Ambiguity due to matching duplicates across left, mid, and right
            if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                left++;
                right--;
                continue; // Re-evaluate bounds with updated edge markers
            }

            // Segment A: Left half is cleanly sorted
            if (nums[left] <= nums[mid]) {
                // Verify if target resides inside the left sorted boundary window
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // Narrow down left
                } else {
                    left = mid + 1;  // Adjust rightward
                }
            } 
            // Segment B: Right half is cleanly sorted
            else {
                // Verify if target resides inside the right sorted boundary window
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // Narrow down right
                } else {
                    right = mid - 1; // Adjust leftward
                }
            }
        }

        return false; // Target cannot be located within the array matrix
    }
}