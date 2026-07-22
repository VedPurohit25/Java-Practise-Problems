import java.util.ArrayList;
import java.util.List;

public class Solution {
    // Return type changed from int[] to List<Integer> to match Driver expectations
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        
        // Count total initial '1's in the string
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }
        
        // Identify all maximal '0' blocks to easily map L0 and R0
        int[] zeroBlockStart = new int[n];
        int[] zeroBlockEnd = new int[n];
        
        int lastStart = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (lastStart == -1) lastStart = i;
                zeroBlockStart[i] = lastStart;
            } else {
                if (lastStart != -1) {
                    for (int j = lastStart; j < i; j++) {
                        zeroBlockEnd[j] = i - 1;
                    }
                    lastStart = -1;
                }
            }
        }
        if (lastStart != -1) {
            for (int j = lastStart; j < n; j++) {
                zeroBlockEnd[j] = n - 1;
            }
        }
        
        // Identify all valid '1' blocks surrounded by '0's
        List<Block> validBlocks = new ArrayList<>();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '1') {
                int start = i;
                while (i < n && s.charAt(i) == '1') {
                    i++;
                }
                int end = i - 1;
                
                // A block must be surrounded by '0's to be eligible for a trade
                if (start > 0 && end < n - 1 && s.charAt(start - 1) == '0' && s.charAt(end + 1) == '0') {
                    int L0 = zeroBlockStart[start - 1];
                    int R0 = zeroBlockEnd[end + 1];
                    validBlocks.add(new Block(start, end, L0, R0));
                }
            } else {
                i++;
            }
        }
        
        int m = validBlocks.size();
        int[] startArr = new int[m];
        int[] endArr = new int[m];
        int[] L0Arr = new int[m];
        int[] R0Arr = new int[m];
        int[] C = new int[m];
        
        for (int j = 0; j < m; j++) {
            Block b = validBlocks.get(j);
            startArr[j] = b.start;
            endArr[j] = b.end;
            L0Arr[j] = b.L0;
            R0Arr[j] = b.R0;
            C[j] = (b.start - b.L0) + (b.R0 - b.end);
        }
        
        // Build Sparse Table for O(1) Range Maximum Queries on internal blocks
        int maxLog = 0;
        while ((1 << maxLog) <= m) {
            maxLog++;
        }
        int[][] st = new int[maxLog + 1][m];
        if (m > 0) {
            for (int j = 0; j < m; j++) {
                st[0][j] = C[j];
            }
            for (int k = 1; k <= maxLog; k++) {
                int len = 1 << (k - 1);
                for (int j = 0; j + len < m; j++) {
                    st[k][j] = Math.max(st[k - 1][j], st[k - 1][j + len]);
                }
            }
        }
        
        // Precompute logarithms for RMQ lookups
        int[] logTable = new int[m + 1];
        for (int j = 2; j <= m; j++) {
            logTable[j] = logTable[j >> 1] + 1;
        }
        
        int numQueries = queries.length;
        List<Integer> answer = new ArrayList<>(numQueries);
        
        for (int q = 0; q < numQueries; q++) {
            int l = queries[q][0];
            int r = queries[q][1];
            
            int idx1 = binarySearchFirst(startArr, l + 1);
            int idx2 = binarySearchLast(endArr, r - 1);
            
            int maxGain = 0;
            if (idx1 <= idx2 && idx1 != -1 && idx2 != -1) {
                if (idx1 == idx2) {
                    maxGain = computeTrueGain(startArr[idx1], endArr[idx1], L0Arr[idx1], R0Arr[idx1], l, r);
                } else {
                    int gainFirst = computeTrueGain(startArr[idx1], endArr[idx1], L0Arr[idx1], R0Arr[idx1], l, r);
                    int gainLast = computeTrueGain(startArr[idx2], endArr[idx2], L0Arr[idx2], R0Arr[idx2], l, r);
                    maxGain = Math.max(gainFirst, gainLast);
                    
                    if (idx1 + 1 <= idx2 - 1) {
                        int len = (idx2 - 1) - (idx1 + 1) + 1;
                        int k = logTable[len];
                        int internalMax = Math.max(st[k][idx1 + 1], st[k][idx2 - 1 - (1 << k) + 1]);
                        maxGain = Math.max(maxGain, internalMax);
                    }
                }
            }
            
            answer.add(totalOnes + maxGain);
        }
        
        return answer;
    }
    
    private int computeTrueGain(int start, int end, int L0, int R0, int l, int r) {
        int leftGain = Math.min(start - L0, start - l);
        int rightGain = Math.min(R0 - end, r - end);
        return leftGain + rightGain;
    }
    
    private int binarySearchFirst(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    
    private int binarySearchLast(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (arr[mid] <= target) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    
    private static class Block {
        int start, end, L0, R0;
        Block(int start, int end, int L0, int R0) {
            this.start = start;
            this.end = end;
            this.L0 = L0;
            this.R0 = R0;
        }
    }
}