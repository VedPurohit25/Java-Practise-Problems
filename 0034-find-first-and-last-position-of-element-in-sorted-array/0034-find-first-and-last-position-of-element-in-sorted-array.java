class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        
        // Find the absolute leftmost index of the target
        result[0] = findBoundary(nums, target, true);
        
        // If the left boundary doesn't exist, the target isn't in the array at all
        if (result[0] != -1) {
            // Find the absolute rightmost index of the target
            result[1] = findBoundary(nums, target, false);
        }
        
        return result;
    }

    private int findBoundary(int[] nums, int target, boolean findFirst) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                index = mid; // Record this valid position
                
                if (findFirst) {
                    right = mid - 1; // Force search to move left for the first occurrence
                } else {
                    left = mid + 1;  // Force search to move right for the last occurrence
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return index;
    }
}