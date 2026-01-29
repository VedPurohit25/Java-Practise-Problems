class Solution {
    public int removeElement(int[] nums, int val) {
        // let k be the array starts from 0 where we wanna to keep separate elements

        int k = 0;

        for(int i =0;i<nums.length;i++){
            if(nums [i] != val){
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
}