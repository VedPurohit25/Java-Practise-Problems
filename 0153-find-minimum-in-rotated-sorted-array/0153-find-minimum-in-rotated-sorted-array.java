class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // Binary Search variant
        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid element is greater than the rightmost element,
            // the inflection point and minimum are in the right half.
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } 
            // Otherwise, the minimum is either at mid or to the left.
            else {
                right = mid;
            }
        }

        // When left == right, we have converged on the minimum element
        return nums[left];
    }
}