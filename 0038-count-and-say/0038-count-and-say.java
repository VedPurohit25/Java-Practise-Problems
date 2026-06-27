class Solution {
    public String countAndSay(int n) {
        // Base case
        if (n <= 0) return "";
        
        String currentString = "1";
        
        // Iteratively build up to the nth sequence
        for (int i = 1; i < n; i++) {
            StringBuilder nextStringBuilder = new StringBuilder();
            int len = currentString.length();
            int count = 1;
            
            for (int j = 0; j < len; j++) {
                // If the next character matches the current one, increment the count
                if (j + 1 < len && currentString.charAt(j) == currentString.charAt(j + 1)) {
                    count++;
                } else {
                    // Append the frequency count followed by the actual digit character
                    nextStringBuilder.append(count).append(currentString.charAt(j));
                    count = 1; // Reset counter for the next distinct block
                }
            }
            
            // Move the pointer forward to process the newly constructed string
            currentString = nextStringBuilder.toString();
        }
        
        return currentString;
    }
}
