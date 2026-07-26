public class Solution {
    public int maximumProduct(int[] nums) {
        // Initialize variables to track the three largest and two smallest elements
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int n : nums) {
            // Find the two smallest integers
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {
                min2 = n;
            }

            // Find the three largest integers
            if (n >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {
                max3 = n;
            }
        }

        // Return the maximum of the two possible products
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}