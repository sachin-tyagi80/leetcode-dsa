class Solution {
    public int removeDuplicates(int[] nums) {
        int j =0;
        for(int i =0;i<nums.length;i++){
            if(nums[j] != nums[i]){
                nums[++j] = nums[i];
            }
        }
        return ++j;
        
    }
}



// "I use two pointers. Pointer i traverses the array, while pointer j keeps track of the last unique element.
// Whenever I find a new unique value, I increment j and place the new value at that position.
// Finally, I return j + 1, which represents the number of unique elements. 
// Since the array is sorted, duplicates are adjacent, making this approach work in O(n) time and O(1) space."
