class Solution {
    public boolean isNumber(String s) {
        // Strip out any trailing or leading whitespaces if present
        s = s.trim();

        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenE = false;
        boolean digitAfterE = true; // True initially so numbers without 'e' pass cleanly

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                seenDigit = true;
                if (seenE) {
                    digitAfterE = true;
                }
            } else if (c == '+' || c == '-') {
                // Signs can only appear at index 0 or right after an exponent 'e/E'
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (c == '.') {
                // A dot cannot appear twice, nor can it appear after an exponent
                if (seenDot || seenE) {
                    return false;
                }
                seenDot = true;
            } else if (c == 'e' || c == 'E') {
                // An exponent cannot appear twice, and must follow a valid digit base
                if (seenE || !seenDigit) {
                    return false;
                }
                seenE = true;
                digitAfterE = false; // We now require a new integer to follow
            } else {
                // Any other character (alphabetic, symbols) is immediately invalid
                return false;
            }
        }

        // The string is only valid if we saw at least one digit and any exponent block was closed
        return seenDigit && digitAfterE;
    }
}