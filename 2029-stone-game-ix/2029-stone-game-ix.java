class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for (int stone : stones) {
            cnt[stone % 3]++;
        }
        
        // If count of 0-modulo stones is even, Alice wins if both 1s and 2s exist
        if (cnt[0] % 2 == 0) {
            return cnt[1] >= 1 && cnt[2] >= 1;
        } 
        // If count of 0-modulo stones is odd, Alice wins if the gap between 1s and 2s is > 2
        else {
            return Math.abs(cnt[1] - cnt[2]) > 2;
        }
    }
}