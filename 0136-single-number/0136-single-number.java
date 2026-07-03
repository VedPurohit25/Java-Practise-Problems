class Solution {
    public int singleNumber(int[] nums) {
        int uniqueElement = 0;
        
        // XOR every element in the array
        for (int num : nums) {
            uniqueElement ^= num;
        }
        
        return uniqueElement;
    }
}