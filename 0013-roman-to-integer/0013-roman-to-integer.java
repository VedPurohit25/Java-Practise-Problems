import java.util.HashMap;
import java.util.Map;

class Solution {
    public int romanToInt(String s) {
        // Map the classical tokens to their decimal weight
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        
        int total = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            int currentVal = romanMap.get(s.charAt(i));
            
            // Look ahead to the next symbol if it exists
            if (i < n - 1 && currentVal < romanMap.get(s.charAt(i + 1))) {
                // Subtractive form encountered (e.g., IV, XC)
                total -= currentVal;
            } else {
                // Standard additive form
                total += currentVal;
            }
        }
        
        return total;
    }
}