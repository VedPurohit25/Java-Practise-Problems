class Solution {
    public String smallestPalindrome(String s) {
        // Step 1: Count frequencies of each character from 'a' to 'z'
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        StringBuilder firstHalf = new StringBuilder();
        char oddChar = 0;

        // Step 2: Build the first half in alphabetical order
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                // If the frequency is odd, save it for the middle element
                if (count[i] % 2 != 0) {
                    oddChar = (char) ('a' + i);
                }
                // Append half of the occurrences to the first half
                for (int j = 0; j < count[i] / 2; j++) {
                    firstHalf.append((char) ('a' + i));
                }
            }
        }

        // Step 3: Construct the final palindrome string
        StringBuilder result = new StringBuilder(firstHalf);
        
        // If there is a character with an odd count, place it in the middle
        if (oddChar != 0) {
            result.append(oddChar);
        }
        
        // Append the reversed first half to complete the palindrome
        result.append(new StringBuilder(firstHalf).reverse());

        return result.toString();
    }
}