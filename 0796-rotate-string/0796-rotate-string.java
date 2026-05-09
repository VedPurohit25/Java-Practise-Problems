class Solution {
    public boolean rotateString(String s, String goal) {
        // Step 1: Structural check. 
        // If lengths differ, no amount of shifting will make them equal.
        if (s.length() != goal.length()) {
            return false;
        }

        // Step 2: The Concatenation Trick.
        // (s + s) contains all possible rotations of s.
        return (s + s).contains(goal);
    }
}