class Solution {
    public int numDecodings(String s) {
        // Base Case: An empty string or a string with a leading zero cannot be decoded
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int n = s.length();
        
        // Pointers representing DP[i-2] and DP[i-1] respectively
        int prev2 = 1; // Base condition for empty string prefix match
        int prev1 = 1; // Base condition for single valid character prefix match

        for (int i = 2; i <= n; i++) {
            int currentWays = 0;
            
            // Read single-digit option (s[i-1])
            int singleDigit = s.charAt(i - 1) - '0';
            if (singleDigit >= 1 && singleDigit <= 9) {
                currentWays += prev1;
            }

            // Read double-digit option (s[i-2...i-1])
            int doubleDigit = Integer.parseInt(s.substring(i - 2, i));
            if (doubleDigit >= 10 && doubleDigit <= 26) {
                currentWays += prev2;
            }

            // If a state cannot inherit any pathways, the transmission contains an un-decodable block
            if (currentWays == 0) {
                return 0;
            }

            // Shift states forward for the next loop cycle
            prev2 = prev1;
            prev1 = currentWays;
        }

        return prev1;
    }
}