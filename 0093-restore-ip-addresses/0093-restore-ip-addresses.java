import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        // An IP string must have between 4 and 12 digits to be valid
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }
        
        backtrack(0, 0, s, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int segmentCount, String s, List<String> currentPath, List<String> result) {
        // Base Case: If we have successfully isolated exactly 4 segments
        if (segmentCount == 4) {
            // Confirm that we have consumed the entire input string
            if (start == s.length()) {
                result.add(String.join(".", currentPath));
            }
            return;
        }

        // Optimization: Prune paths if the remaining characters cannot physically fit into the open segments
        int remainingLength = s.length() - start;
        int remainingSegments = 4 - segmentCount;
        if (remainingLength < remainingSegments || remainingLength > remainingSegments * 3) {
            return;
        }

        // Try extracting segment sizes of 1, 2, and 3 digits
        for (int len = 1; len <= 3; len++) {
            if (start + len > s.length()) {
                break;
            }

            String part = s.substring(start, start + len);

            // Protocol Check: Validate leading zeros and range criteria [0, 255]
            if ((part.length() > 1 && part.charAt(0) == '0') || Integer.parseInt(part) > 255) {
                continue; // Invalid segment configuration: bypass branch
            }

            // Choose: Append valid segment block to path
            currentPath.add(part);

            // Explore: Step deeper to capture the next network segment
            backtrack(start + len, segmentCount + 1, s, currentPath, result);

            // Un-choose: Backtrack by popping the last entry to refresh the tracking register
            currentPath.remove(currentPath.size() - 1);
        }
    }
}