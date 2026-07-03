import java.util.Arrays;

class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        
        int n = ratings.length;
        int[] candies = new int[n];
        
        // Requirement 1: Each child must have at least one candy.
        Arrays.fill(candies, 1);
        
        // Pass 1: Left-to-Right
        // If the current child has a higher rating than the left neighbor,
        // they must receive more candies than the left neighbor.
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        
        // Pass 2: Right-to-Left
        // If the current child has a higher rating than the right neighbor,
        // they must receive more candies than the right neighbor.
        // We use Math.max to make sure we don't violate the Left-to-Right condition.
        int totalCandies = candies[n - 1]; // Initialize total with the last child's candy
        
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            totalCandies += candies[i];
        }
        
        return totalCandies;
    }
}