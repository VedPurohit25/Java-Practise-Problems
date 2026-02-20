 class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // Since constraints say 0 <= arr1[i] <= 1000
        int[] count = new int[1001];
        int[] result = new int[arr1.length];
        
        // 1. Fill the frequency array
        for (int num : arr1) {
            count[num]++;
        }
        
        int index = 0;
        // 2. Add elements in the order they appear in arr2
        for (int num : arr2) {
            while (count[num] > 0) {
                result[index++] = num;
                count[num]--;
            }
        }
        
        // 3. Add remaining elements (those not in arr2) in ascending order
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                result[index++] = i;
                count[i]--;
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        int[] res = sol.relativeSortArray(arr1, arr2);
        
        for (int n : res) System.out.print(n + " "); 
        // Output: 2 2 2 1 4 3 3 9 6 7 19
    }
}