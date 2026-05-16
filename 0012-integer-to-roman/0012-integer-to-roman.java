class Solution {
    public String intToRoman(int num) {
        // Define the structural milestones in descending order
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder roman = new StringBuilder();
        
        // Loop through the milestones
        for (int i = 0; i < values.length; i++) {
            // While the current milestone can be subtracted from num
            while (num >= values[i]) {
                roman.append(symbols[i]);
                num -= values[i]; // Reduce the balance
            }
        }
        
        return roman.toString();
    }
}