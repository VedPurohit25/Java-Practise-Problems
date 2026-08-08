import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // Store occurrence indices of each character in word1
        List<Integer>[] posList = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            posList[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            posList[word1.charAt(i) - 'a'].add(i);
        }
        
        // Precompute last[k]: latest starting index in word1 
        // to match word2[k...m-1] with 0 mismatches.
        int[] last = new int[m + 1];
        last[m] = n;
        
        for (int k = m - 1; k >= 0; k--) {
            if (last[k + 1] == -1) {
                last[k] = -1;
            } else {
                int charIdx = word2.charAt(k) - 'a';
                last[k] = findLastLessThan(posList[charIdx], last[k + 1]);
            }
        }
        
        int[] seq = new int[m];
        int usedMismatch = 0;
        
        for (int i = 0; i < m; i++) {
            int prev = (i == 0) ? -1 : seq[i - 1];
            
            if (usedMismatch == 0) {
                int nextIdx = prev + 1;
                
                if (nextIdx < n && word1.charAt(nextIdx) == word2.charAt(i)) {
                    // Choice 1: Exact match at prev + 1
                    seq[i] = nextIdx;
                } else if (nextIdx < n && last[i + 1] > nextIdx) {
                    // Choice 2: Spend mismatch at prev + 1
                    seq[i] = nextIdx;
                    usedMismatch = 1;
                } else {
                    // Choice 3: Must find the first exact match after prev
                    int charIdx = word2.charAt(i) - 'a';
                    int j = findFirstGreaterThan(posList[charIdx], prev);
                    if (j == -1) {
                        return new int[0];
                    }
                    seq[i] = j;
                }
            } else {
                // Must match word2[i] exactly
                int charIdx = word2.charAt(i) - 'a';
                int j = findFirstGreaterThan(posList[charIdx], prev);
                
                if (j == -1 || last[i + 1] <= j) {
                    return new int[0];
                }
                seq[i] = j;
            }
        }
        
        return seq;
    }
    
    // Finds the largest element in list that is strictly < target
    private int findLastLessThan(List<Integer> list, int target) {
        int low = 0, high = list.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (list.get(mid) < target) {
                ans = list.get(mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    
    // Finds the smallest element in list that is strictly > target
    private int findFirstGreaterThan(List<Integer> list, int target) {
        int low = 0, high = list.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (list.get(mid) > target) {
                ans = list.get(mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}