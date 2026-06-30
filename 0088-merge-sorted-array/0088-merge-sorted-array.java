class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Trackers for the trailing valid elements of both segments
        int p1 = m - 1;
        int p2 = n - 1;
        // The write pointer anchored to the absolute end of the array
        int p = m + n - 1;

        // Step 1: Merge elements from back to front, picking the larger value
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }

        // Step 2: Cleanup any remaining elements left over inside nums2
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
        // Note: Remaining elements in nums1 are already sorted and in place!
    }
}