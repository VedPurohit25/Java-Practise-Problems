class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] count = new int[101]; // Since nums[i] <= 100
        int maxFreq = 0;
        
        // Step 1 & 2: Count frequencies and track the maximum
        for (int num : nums) {
            count[num]++;
            maxFreq = Math.max(maxFreq, count[num]);
        }
        
        // Step 3: Sum frequencies of all elements that reached maxFreq
        int totalFrequency = 0;
        for (int f : count) {
            if (f == maxFreq) {
                totalFrequency += f;
            }
        }
        
        return totalFrequency;
    }
}