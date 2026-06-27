class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        
        int n = nums.length;
        int i = n - 2;
        
        // Step 1: Scan from right to left to find the first decreasing element (the pivot)
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        // Step 2: If a valid pivot is identified, find the successor to swap with
        if (i >= 0) {
            int j = n - 1;
            // Find the smallest element on the right that is larger than nums[i]
            while (nums[j] <= nums[i]) {
                j--;
            }
            // Swap the pivot with its successor
            swap(nums, i, j);
        }
        
        // Step 3: Reverse the descending tail to make it ascending (smallest possible increase)
        reverse(nums, i + 1, n - 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}