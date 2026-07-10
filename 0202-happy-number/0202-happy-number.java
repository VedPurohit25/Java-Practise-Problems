public class Solution {
    
    // Helper method to calculate the sum of the squares of digits
    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        int tortoise = n;
        int hare = getNext(n);
        
        // Move pointers until they meet or hare reaches 1
        while (hare != 1 && tortoise != hare) {
            tortoise = getNext(tortoise);          // 1 step
            hare = getNext(getNext(hare));        // 2 steps
        }
        
        // If hare reached 1, it's a happy number
        return hare == 1;
    }
}