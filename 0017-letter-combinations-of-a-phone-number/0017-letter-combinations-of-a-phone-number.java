import java.util.ArrayList;
import java.util.List;

class Solution {
    // Static keypad mapping where index matches the digit
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        
        // Edge case: empty input string
        if (digits == null || digits.isEmpty()) {
            return combinations;
        }
        
        // Initiate the backtracking search from index 0
        backtrack(digits, 0, new StringBuilder(), combinations);
        return combinations;
    }

    private void backtrack(String digits, int index, StringBuilder currentPath, List<String> combinations) {
        // Base Case: If the path matches the input length, a complete combination is formed
        if (index == digits.length()) {
            combinations.add(currentPath.toString());
            return;
        }

        // Get the letters corresponding to the current digit
        char digitChar = digits.charAt(index);
        String letters = KEYPAD[digitChar - '0'];

        // Explore every branch for this digit
        for (int i = 0; i < letters.length(); i++) {
            currentPath.append(letters.charAt(i)); // Make a choice
            
            backtrack(digits, index + 1, currentPath, combinations); // Move to the next digit
            
            currentPath.deleteCharAt(currentPath.length() - 1); // Backtrack (undo the choice)
        }
    }
}