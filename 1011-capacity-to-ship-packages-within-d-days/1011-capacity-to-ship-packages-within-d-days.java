public class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for (int w : weights) {
            left = Math.max(left, w); // Min capacity = max weight
            right += w;               // Max capacity = total sum
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canShip(weights, days, mid)) {
                right = mid; // Try smaller capacity
            } else {
                left = mid + 1; // Need larger capacity
            }
        }
        return left;
    }

    private boolean canShip(int[] weights, int days, int capacity) {
        int currentDays = 1;
        int currentLoad = 0;
        
        for (int w : weights) {
            if (currentLoad + w > capacity) {
                currentDays++;
                currentLoad = 0;
            }
            currentLoad += w;
        }
        
        return currentDays <= days;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;
        System.out.println("Minimum capacity: " + sol.shipWithinDays(weights, days));
    }
}