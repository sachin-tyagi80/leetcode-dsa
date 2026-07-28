class Solution {
    public int maxProduct(int[] nums) {
        // Arrays.sort(nums); // O(nlogn)

        // int n = nums.length;
        // return (nums[n-1]-1) * (nums[n-2]-1);

        int max1 = 0;
        int max2 = 0;
        for(int num : nums){ // O(n)
            if(num>max1){
                max2 = max1;
                max1 = num;
            }else if(num>max2){
                max2 = num;
            }
        }
        return (max1 -1)*(max2-1);


        
    }
}


// "Yes, sorting is a valid solution. After sorting in ascending order, the last two elements are the largest, so their (value - 1) product gives the maximum result.
// This takes O(n log n) time. However, we can optimize it to O(n) by finding the largest and second-largest elements in a single pass without sorting."
