class Solution {
    public boolean canJump(int[] nums) {
        int maxReach =0;  // Maximum index we can reach
        for(int i=0;i<nums.length;i++){
            if(i>maxReach){    // Current index is unreachable
                return false;
            }
            maxReach = Math.max(maxReach,i+nums[i   // Update the farthest reachable index

        }
        return true;
        
    }
}


// Interview Explanation (English)

// "I use a Greedy approach. I maintain the farthest index that can be reached so far using a variable maxReach. While traversing the array,
// if the current index is greater than maxReach, it means that index is unreachable, so I return false. Otherwise, I update maxReach as max(maxReach,i + nums[i]).
// If I finish the traversal, it means the last index is reachable."
