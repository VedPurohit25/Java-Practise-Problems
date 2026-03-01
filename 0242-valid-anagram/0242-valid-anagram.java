import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> counts = new HashMap<>();

        // Build the frequency map using string s
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        // Subtract frequencies using string t
        for (char c : t.toCharArray()) {
            if (!counts.containsKey(c) || counts.get(c) == 0) {
                return false;
            }
            counts.put(c, counts.get(c) - 1);
        }

        return true;
    }
}