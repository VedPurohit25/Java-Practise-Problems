class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        
        // Base Case: Any array with 2 or fewer elements is already perfectly valid
        if (n <= 2) {
            return n;
        }

        // Initialize the write anchor pointer. The first two slots are always safe.
        int k = 2;

        // Scan through the remaining elements using the fast reader pointer i
        for (int i = 2; i < n; i++) {
            // Compare the current element with the element two slots behind the write boundary
            if (nums[i] != nums[k - 2]) {
                nums[k] = nums[i]; // Commit the valid element to the write anchor position
                k++;               // Advance the valid boundary pointer
            }
        }

        // Return the final size of the compacted array prefix
        return k;
    }
}