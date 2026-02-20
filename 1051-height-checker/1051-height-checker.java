class Solution {
    public int heightChecker(int[] heights) {
        int[] sortedHeights = new int[heights.length];
        int[] freq = new int[101];
        for(int height : heights){
            freq[height]++;
        }

        // prefix sum
        for(int i=1; i<freq.length; i++){
            freq[i] += freq[i-1];
        }

        for(int i=heights.length-1; i>=0; i--){
            sortedHeights[freq[heights[i]]-1] = heights[i];
            freq[heights[i]]--;
        }

        int ans = 0;
        for(int i=0; i<heights.length; i++){
            if(heights[i] != sortedHeights[i]) ans++;
        }

        return ans;
    }
}