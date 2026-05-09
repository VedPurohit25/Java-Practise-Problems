class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            // Check for potential positive overflow
            // 2147483647 is the max. If rev > 214748364, next * 10 will overflow.
            // If rev == 214748364, and pop > 7, it will overflow.
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            
            // Check for potential negative overflow
            // -2147483648 is the min. If rev < -214748364, next * 10 will overflow.
            // If rev == -214748364, and pop < -8, it will overflow.
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            
            rev = rev * 10 + pop;
        }
        return rev;
    }
}