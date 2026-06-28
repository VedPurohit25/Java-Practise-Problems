import java.util.Arrays;

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        // Step 1: Rearrange elements to enable monotonic progression
        Arrays.sort(arr);
        
        // Step 2: Set the mandatory foundation baseline height
        int currentMaxHeight = 1;
        
        // Step 3: Walk through the sorted array and step up where possible
        for (int i = 1; i < arr.length; i++) {
            // If the element is large enough to allow a height increase
            if (arr[i] > currentMaxHeight) {
                currentMaxHeight++; // Step up by exactly 1
            }
            // If it's smaller or equal, it can at best match or support currentMaxHeight
        }
        
        return currentMaxHeight;
    }
}