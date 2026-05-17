class Solution {
    public boolean canReach(int[] arr, int start) {
        // Base Case 1: Out of bounds check
        if (start < 0 || start >= arr.length) {
            return false;
        }
        
        // Base Case 2: Already visited check (marked by negative values)
        if (arr[start] < 0) {
            return false;
        }
        
        // Base Case 3: Target identified
        if (arr[start] == 0) {
            return true;
        }
        
        // Capture the jump capability of the current index
        int jumpValue = arr[start];
        
        // Mark as visited in-place by flipping the sign of the element
        arr[start] = -arr[start];
        
        // Recursively explore the forward branch OR the backward branch
        boolean found = canReach(arr, start + jumpValue) || canReach(arr, start - jumpValue);
        
        // Optional: Restore array values if data mutability is restricted
        // arr[start] = jumpValue;
        
        return found;
    }
}