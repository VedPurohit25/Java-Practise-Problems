import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        // Start the recursive construction with 0 open and 0 close brackets
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder currentString, int open, int close, int max) {
        // Base Case: The structure is perfectly balanced and complete
        if (currentString.length() == max * 2) {
            result.add(currentString.toString());
            return;
        }

        // Rule 1: Add an opening bracket if we haven't hit the maximum limit
        if (open < max) {
            currentString.append('(');
            backtrack(result, currentString, open + 1, close, max);
            currentString.deleteCharAt(currentString.length() - 1); // Undo choice
        }

        // Rule 2: Add a closing bracket only if it balances an existing open bracket
        if (close < open) {
            currentString.append(')');
            backtrack(result, currentString, open, close + 1, max);
            currentString.deleteCharAt(currentString.length() - 1); // Undo choice
        }
    }
}