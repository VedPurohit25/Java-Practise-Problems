import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public String largestNumber(int[] nums) {
        // Convert the integer array to a String array
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        // Sort strings using the custom comparator
        Arrays.sort(asStrs, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String order1 = a + b;
                String order2 = b + a;
                // Sort in descending order of concatenation value
                return order2.compareTo(order1);
            }
        });

        // Edge case: If the largest number is "0", the entire number is 0
        // (e.g., nums = [0, 0])
        if (asStrs[0].equals("0")) {
            return "0";
        }

        // Build the final largest number string
        StringBuilder largestNumberStr = new StringBuilder();
        for (String numAsStr : asStrs) {
            largestNumberStr.append(numAsStr);
        }

        return largestNumberStr.toString();
    }
}