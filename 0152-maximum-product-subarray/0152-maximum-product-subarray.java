class Solution {
    public int maxProduct(int[] nums) {

        int maxProduct = nums[0];
        int minProduct = nums[0];
        int ans = nums[0];

        for(int i =1;i<nums.length;i++){
            if(nums[i]<0){
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }
            maxProduct = Math.max(nums[i],nums[i]*maxProduct);
            minProduct = Math.min(nums[i],nums[i]*minProduct);
            ans = Math.max(ans,maxProduct);
        }
        return ans;
    }
}



// "I maintain both the maximum and minimum product ending at the current index because a negative number can turn the smallest product into the largest one. 
// Whenever I encounter a negative element, I swap the current maximum and minimum products before updating them. 
// Then I update the answer with the maximum product seen so far. 
// Since I process each element only once, the time complexity is O(n) and the space complexity is O(1)."
