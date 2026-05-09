class Solution {
    public int maxProfit(int[] prices) {
        int totalProfit = 0;
        
        // Start from the second day
        for (int i = 1; i < prices.length; i++) {
            // If the price increased compared to the previous day
            if (prices[i] > prices[i - 1]) {
                // Capture the profit immediately
                totalProfit += prices[i] - prices[i - 1];
            }
        }
        
        return totalProfit;
    }
}