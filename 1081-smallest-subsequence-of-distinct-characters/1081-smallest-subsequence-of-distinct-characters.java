import java.util.*;

class Solution {
    public String smallestSubsequence(String s) {
        // 1. Calculate the total frequency of each character in the string
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        // 2. Track characters currently inside our output stack layout
        boolean[] inStack = new boolean[26];
        
        // Use a simple char array to emulate a high-performance primitive stack
        char[] stack = new char[26];
        int top = -1; 
        
        // 3. Scan the string sequentially
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = c - 'a';
            
            // If the character is already tracking in our stack, skip it
            if (inStack[idx]) {
                continue;
            }
            
            // Pop larger characters from the stack if they appear again later
            while (top >= 0 && stack[top] > c && lastIndex[stack[top] - 'a'] > i) {
                inStack[stack[top] - 'a'] = false; // Mark as no longer in stack
                top--;
            }
            
            // Push the current character onto the stack layout
            stack[++top] = c;
            inStack[idx] = true;
        }
        
        // 4. Construct the final subsequence string from the primitive stack
        return new String(stack, 0, top + 1);
    }
}