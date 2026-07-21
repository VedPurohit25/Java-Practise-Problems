import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        
        // Count initial '1's in s
        int initialOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                initialOnes++;
            }
        }
        
        // Build the augmented string structure directly
        List<Integer> onesBlocks = new ArrayList<>();
        List<Integer> zerosBlocks = new ArrayList<>();
        
        int idx = 0;
        
        // First '1' block includes the augmented '1'
        int firstOneBlock = 1;
        while (idx < n && s.charAt(idx) == '1') {
            firstOneBlock++;
            idx++;
        }
        onesBlocks.add(firstOneBlock);
        
        while (idx < n) {
            // Read '0' block
            int zeroLen = 0;
            while (idx < n && s.charAt(idx) == '0') {
                zeroLen++;
                idx++;
            }
            zerosBlocks.add(zeroLen);
            
            // Read '1' block
            int oneLen = 0;
            while (idx < n && s.charAt(idx) == '1') {
                oneLen++;
                idx++;
            }
            // If we reached the end of s, this last '1' block gets the augmented '1'
            if (idx == n) {
                oneLen += 1;
            }
            onesBlocks.add(oneLen);
        }
        
        // If s ended with '0', the last block of '1's is just the augmented '1'
        if (s.charAt(n - 1) == '0') {
            onesBlocks.add(1);
        }
        
        int k = zerosBlocks.size();
        // If there are no internal '1' blocks, no valid trade can be made.
        if (k < 2) {
            return initialOnes;
        }
        
        // Find the maximum and second maximum '0' block sizes and their indices
        int maxB1 = -1, maxB2 = -1;
        int maxB1Idx = -1;
        
        for (int i = 0; i < k; i++) {
            int val = zerosBlocks.get(i);
            if (val > maxB1) {
                maxB2 = maxB1;
                maxB1 = val;
                maxB1Idx = i;
            } else if (val > maxB2) {
                maxB2 = val;
            }
        }
        
        int maxGain = 0;
        
        // Iterate through all internal '1' blocks
        for (int i = 1; i < k; i++) {
            int A_i = onesBlocks.get(i);
            int B_left = zerosBlocks.get(i - 1);
            int B_right = zerosBlocks.get(i);
            
            // Option 1: Convert the combined giant '0' block back to '1's
            int gain1 = B_left + B_right;
            
            // Option 2: Convert a completely different '0' block back to '1's
            int maxOtherB = 0;
            if (maxB1Idx != i - 1 && maxB1Idx != i) {
                maxOtherB = maxB1;
            } else {
                maxOtherB = maxB2;
            }
            int gain2 = maxOtherB - A_i;
            
            maxGain = Math.max(maxGain, Math.max(gain1, gain2));
        }
        
        return initialOnes + maxGain;
    }
}