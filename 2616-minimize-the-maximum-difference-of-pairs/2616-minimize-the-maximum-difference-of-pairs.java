import java.util.Arrays;

class Solution {
    public int minimizeMax(int[] nums, int p) {
        if (p == 0) return 0;
        Arrays.sort(nums);
        
        int n = nums.length;
        int left = 0;
        int right = nums[n - 1] - nums[0];
        int ans = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canFormPairs(nums, p, mid)) {
                ans = mid;
                right = mid - 1; // Try for an even smaller max difference
            } else {
                left = mid + 1; // Need a larger threshold to find p pairs
            }
        }
        return ans;
    }
    
    private boolean canFormPairs(int[] nums, int p, int threshold) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; ) {
            // Greedy check: if adjacent elements fit within threshold
            if (nums[i + 1] - nums[i] <= threshold) {
                count++;
                i += 2; // Jump 2 because index cannot be reused
            } else {
                i++; // Try the next adjacent pair
            }
            if (count >= p) return true;
        }
        return count >= p;
    }
}