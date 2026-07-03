import java.util.*;

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        // Backtrack starting from index 0 with an empty current partition list
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> currentPartition, List<List<String>> result) {
        // Base case: If we've processed the entire string, add the current partition path to results
        if (start == s.length()) {
            result.add(new ArrayList<>(currentPartition));
            return;
        }

        // Explore all possible substrings starting from 'start' index
        for (int end = start; end < s.length(); end++) {
            // Check if the substring s[start...end] is a palindrome
            if (isPalindrome(s, start, end)) {
                // Action: Choose the valid palindrome substring
                currentPartition.add(s.substring(start, end + 1));
                
                // Recurse: Move the start pointer right after the current substring
                backtrack(s, end + 1, currentPartition, result);
                
                // Backtrack: Undo the choice for the next iteration loops
                currentPartition.remove(currentPartition.size() - 1);
            }
        }
    }

    // High-speed two-pointer check to verify palindromes in-place
    private boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    }
}