public class Solution {
    public int findGCD(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        
        // Single pass to find the minimum and maximum elements
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        
        // Compute GCD of the min and max found
        return gcd(max, min);
    }
    
    // Helper method using the Euclidean Algorithm
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}