import java.util.HashMap;
import java.util.Map;

public class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        
        StringBuilder res = new StringBuilder();
        
        // Step 1: Determine the sign
        // If one is negative and the other is positive, the result is negative
        if ((numerator < 0) ^ (denominator < 0)) {
            res.append("-");
        }
        
        // Step 2: Convert to absolute long to prevent integer overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        
        // Step 3: Compute the integral part (before the decimal point)
        res.append(num / den);
        long remainder = num % den;
        
        // If there's no remainder, we are done
        if (remainder == 0) {
            return res.toString();
        }
        
        // Step 4: Compute the fractional part
        res.append(".");
        
        // Map to store (remainder -> index in StringBuilder where this remainder occurred)
        Map<Long, Integer> map = new HashMap<>();
        
        while (remainder != 0) {
            // If the remainder has been seen before, a cycle is detected
            if (map.containsKey(remainder)) {
                int index = map.get(remainder);
                res.insert(index, "(");
                res.append(")");
                break;
            }
            
            // Record the position of the current remainder before manipulating it
            map.put(remainder, res.length());
            
            // Core division mechanism: multiply remainder by 10 and divide
            remainder *= 10;
            res.append(remainder / den);
            remainder %= den;
        }
        
        return res.toString();
    }
}