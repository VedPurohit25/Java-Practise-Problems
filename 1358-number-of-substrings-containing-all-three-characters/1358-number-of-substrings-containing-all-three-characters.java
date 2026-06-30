class Solution {
    public int numberOfSubstrings(String s) {
        // Registers to track the last seen index of each signal
        int lastA = -1;
        int lastB = -1;
        int lastC = -1;
        
        int totalSubstrings = 0;
        int len = s.length();

        // Linear sweep across the string tracking the end boundary
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);

            // Update the last seen position of the active character
            if (ch == 'a') lastA = i;
            else if (ch == 'b') lastB = i;
            else if (ch == 'c') lastC = i;

            // If all three characters have been observed at least once
            if (lastA != -1 && lastB != -1 && lastC != -1) {
                // The limiting bottleneck index determines the valid start options
                int minIndex = Math.min(lastA, Math.min(lastB, lastC));
                
                // Add all valid substrings starting from index 0 up to minIndex
                totalSubstrings += (minIndex + 1);
            }
        }

        return totalSubstrings;
    }
}