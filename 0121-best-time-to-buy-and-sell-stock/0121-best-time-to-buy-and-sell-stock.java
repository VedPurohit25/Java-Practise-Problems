class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // Initialize the valley marker to the first available price checkpoint
        int minPrice = prices[0];
        // Maximum profit starts at 0 (representing no viable transaction)
        int maxProfit = 0;

        // Sweep sequentially through the remaining days of the matrix timeline
        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];

            if (currentPrice < minPrice) {
                // Track the new valley baseline configuration
                minPrice = currentPrice;
            } else {
                // Calculate the potential spread margin if sold at today's value
                int potentialProfit = currentPrice - minPrice;
                if (potentialProfit > maxProfit) {
                    maxProfit = potentialProfit;
                }
            }
        }

        return maxProfit;
    }
}