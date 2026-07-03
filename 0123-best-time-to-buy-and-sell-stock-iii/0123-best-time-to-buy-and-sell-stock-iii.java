class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // Initialize states for day 1
        int firstBuy = -prices[0];
        int firstSell = 0;
        int secondBuy = Integer.MIN_VALUE;
        int secondSell = 0;

        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];

            // 1. Maximize money after buying the first stock (minimize cost)
            if (-price > firstBuy) {
                firstBuy = -price;
            }

            // 2. Maximize profit after selling the first stock
            if (firstBuy + price > firstSell) {
                firstSell = firstBuy + price;
            }

            // 3. Maximize money after buying the second stock (reinvesting firstSell)
            if (firstSell - price > secondBuy) {
                secondBuy = firstSell - price;
            }

            // 4. Maximize absolute final profit after selling the second stock
            if (secondBuy + price > secondSell) {
                secondSell = secondBuy + price;
            }
        }

        return secondSell;
    }
}