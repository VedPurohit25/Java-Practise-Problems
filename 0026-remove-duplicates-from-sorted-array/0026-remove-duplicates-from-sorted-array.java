class Solution {
    public int removeDuplicates(int[] nums) {
        int count = 1;

        int i = 0;
        int j = i+1;

        while(j< nums.length){
            if (nums[i] == nums[j]){
                j++;
            }else{
                //swaping 

                int temp = nums[j];
                nums[j] = nums[count];
                nums[count] = temp;

                count++;
                i++;
                j++;
            }
        }
        return count;
    }
}