class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1: Place each number in its correct bucket index if possible
        for (int i = 0; i < n; i++) {
            // Keep swapping until the element at index i is in its correct home position,
            // or falls completely out of our valid tracking boundaries [1, n].
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // Swap nums[i] with the element at its target index (nums[i] - 1)
                swap(nums, i, nums[i] - 1);
            }
        }

        // Step 2: Scan from left to right to find the first broken index alignment
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1; // The expected positive integer at this index is missing
            }
        }

        // Step 3: If all spots are perfectly filled, the answer is the next integer
        return n + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}