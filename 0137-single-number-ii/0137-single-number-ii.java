class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;
        
        for (int num : nums) {
            // 'ones' will hold bits that appear 1st time or 4th time...
            // but we filter out bits that are already in 'twos'
            ones = (ones ^ num) & ~twos;
            
            // 'twos' will hold bits that appear 2nd time or 5th time...
            // but we filter out bits that are now in 'ones'
            twos = (twos ^ num) & ~ones;
        }
        
        // After processing everything, 'ones' holds the unique number
        return ones;
    }
}