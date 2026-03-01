class Solution {
    public boolean isAnagram(String s, String t) {
        // Step 1: Anagrams must have the same length
        if (s.length() != t.length()) {
            return false;
        }

        int[] alphabet = new int[26];

        // Step 2 & 3: Tally up s and tally down t
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
            alphabet[t.charAt(i) - 'a']--;
        }

        // Step 4: If all counts are zero, it's a perfect match
        for (int count : alphabet) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}