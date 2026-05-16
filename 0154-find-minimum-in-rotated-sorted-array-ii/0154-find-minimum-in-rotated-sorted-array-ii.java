class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Minimum is definitely in the right sub-array
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // Minimum is at mid or in the left sub-array
                right = mid;
            } else {
                // Foggy scenario: nums[mid] == nums[right]
                // We cannot determine the side, so we step down securely
                right--;
            }
        }

        // Left and right converge securely on the absolute minimum
        return nums[left];
    }
}