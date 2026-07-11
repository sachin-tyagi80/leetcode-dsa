class Solution {
    public int maxSubArray(int[] nums) {
        int currSum = nums[0];
        int maxSum = nums[0];


        for(int i=1;i<nums.length;i++){
            currSum = Math.max(nums[i],currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
        
    }
}



// "I use Kadane's Algorithm. I maintain a running sum of the current subarray and, at each element, 
// decide whether to extend the current subarray or start a new one from the current element. 
// I update the maximum sum seen so far during the traversal. This solution runs in O(n) time and uses O(1) extra space."
