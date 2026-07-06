public class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // If we are on an upward slope, the peak must be to the right
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                // If we are on a downward slope, the peak is either mid or to the left
                right = mid;
            }
        }
        
        // left and right converge to the peak element index
        return left;
    }
}