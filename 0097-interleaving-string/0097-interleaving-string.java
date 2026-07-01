class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        // Step 1: Pre-flight validation check on length alignment
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        int r = s1.length();
        int c = s2.length();
        boolean[][] dp = new boolean[r + 1][c + 1];

        // Base case: empty strings align perfectly
        dp[0][0] = true;

        // Step 2: Initialize the first column (matching s3 prefixes using only s1)
        for (int i = 1; i <= r; i++) {
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }

        // Step 3: Initialize the first row (matching s3 prefixes using only s2)
        for (int j = 1; j <= c; j++) {
            dp[0][j] = dp[0][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
        }

        // Step 4: Fill out the inner state matrix
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                int targetCharIdx = i + j - 1;
                
                // Inherit true if it matches s1's character and the preceding s1 path is valid
                boolean matchS1 = dp[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(targetCharIdx));
                
                // Inherit true if it matches s2's character and the preceding s2 path is valid
                boolean matchS2 = dp[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(targetCharIdx));
                
                dp[i][j] = matchS1 || matchS2;
            }
        }

        return dp[r][c];
    }
}