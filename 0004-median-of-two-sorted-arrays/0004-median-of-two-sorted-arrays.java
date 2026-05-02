class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to optimize binary search range
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0, high = m;

        while (low <= high) {
            // Partition index for nums1
            int partition1 = (low + high) / 2;
            // Partition index for nums2 to ensure equal halves
            int partition2 = (m + n + 1) / 2 - partition1;

            // Edge cases: if partition is at the very beginning or end
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : nums1[partition1 - 1];
            int minRight1 = (partition1 == m) ? Integer.MAX_VALUE : nums1[partition1];

            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : nums2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : nums2[partition2];

            // Check if we found the correct partition
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // If total number of elements is odd
                if ((m + n) % 2 != 0) {
                    return Math.max(maxLeft1, maxLeft2);
                }
                // If total is even
                return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
            } 
            // Too far right in nums1, move left
            else if (maxLeft1 > minRight2) {
                high = partition1 - 1;
            } 
            // Too far left in nums1, move right
            else {
                low = partition1 + 1;
            }
        }
        return 0.0;
    }
}