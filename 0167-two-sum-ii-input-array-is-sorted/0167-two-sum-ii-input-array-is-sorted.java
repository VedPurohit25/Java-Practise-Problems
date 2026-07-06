public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        
        while (left < right) {
            int currentSum = numbers[left] + numbers[right];
            
            if (currentSum == target) {
                // Return 1-indexed results
                return new int[]{left + 1, right + 1};
            } else if (currentSum < target) {
                // Sum is too small, move left pointer rightward to get a larger value
                left++;
            } else {
                // Sum is too large, move right pointer leftward to get a smaller value
                right--;
            }
        }
        
        // Return an empty array if no solution is found (though the problem guarantees one)
        return new int[]{-1, -1};
    }
}