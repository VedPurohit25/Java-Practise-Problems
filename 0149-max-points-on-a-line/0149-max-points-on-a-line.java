import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n; // 1 or 2 points always form a line
        
        int maxPointsOnLine = 1;
        
        // Iterate through each point as the anchor/base point
        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int currentMax = 0;
            
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                // Reduce the fraction by dividing by the GCD
                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;
                
                // Standardize the sign to ensure consistency (e.g., -1/2 vs 1/-2)
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    // Vertical line handling
                    dy = 1; 
                }
                
                String slope = dy + "/" + dx;
                slopeMap.put(slope, slopeMap.getOrDefault(slope, 0) + 1);
                currentMax = Math.max(currentMax, slopeMap.get(slope));
            }
            
            // +1 includes the anchor point 'i' itself
            maxPointsOnLine = Math.max(maxPointsOnLine, currentMax + 1);
        }
        
        return maxPointsOnLine;
    }
    
    // Helper method to find the Greatest Common Divisor (GCD)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}