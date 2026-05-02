class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        
        // Step 1: Calculate Prefix Products
        // answer[i] will hold the product of all elements to the left of i
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }
        
        // Step 2: Calculate Suffix Products on the fly
        // Use a running variable 'rightProduct' to keep track of suffix
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            // Final answer is current prefix (already in answer[i]) * current suffix
            answer[i] = answer[i] * rightProduct;
            
            // Update the suffix for the next element to the left
            rightProduct *= nums[i];
        }
        
        return answer;
    }
}