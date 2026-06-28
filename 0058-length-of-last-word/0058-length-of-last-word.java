class Solution {
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        int length = 0;

        // Phase 1: Clean trailing spaces by shifting the pointer backward
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Phase 2: Accumulate the length of the last contiguous non-space word
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }

        return length;
    }
}