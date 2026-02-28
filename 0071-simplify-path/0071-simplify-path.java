import java.util.*;

class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        // Use Scanner to split the string by the slash '/' delimiter
        Scanner scanner = new Scanner(path);
        scanner.useDelimiter("/");

        while (scanner.hasNext()) {
            String part = scanner.next();
            
            // Skip empty strings (from //) or current directory (.)
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }
            
            if (part.equals("..")) {
                // Go up one level if possible
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // Valid directory name (including "...")
                stack.push(part);
            }
        }
        scanner.close();

        // Reconstruct the canonical path
        if (stack.isEmpty()) return "/";
        
        StringBuilder sb = new StringBuilder();
        for (String dir : stack) {
            sb.append("/").append(dir);
        }
        
        return sb.toString();
    }
}