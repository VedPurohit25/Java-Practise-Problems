class Solution {
    public int reverse(int x) {
        int result = 0;
        
        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            
            // The "Identity Check": 
            // If (newResult - tail) / 10 doesn't equal our previous result,
            // an overflow MUST have occurred during the multiplication/addition.
            if ((newResult - tail) / 10 != result) {
                return 0;
            }
            
            result = newResult;
            x = x / 10;
        }
        
        return result;
    }
}