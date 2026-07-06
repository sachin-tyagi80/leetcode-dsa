class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int maxCount = 0;
        for(int num : nums){
            if(num == 1){
                count++;
                maxCount = Math.max(maxCount,count);
            }else{
                count = 0;
            }
        }
        return maxCount;
        
    }
}


// "I traverse the array once while maintaining two variables: 
// one for the current consecutive count of 1's and another for the maximum count found so far. If the current element is 1, 
// I increment the count and update the maximum. If it is 0, I reset the count because the consecutive sequence breaks. 
// This solution runs in O(n) time and O(1) extra space."
