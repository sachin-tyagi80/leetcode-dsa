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

// Interviewer:
// Why do we return false when i > maxReach?
// Best Answer (English)
// "The variable maxReach stores the farthest index that can be reached using all the jumps we've considered so far. If the current index i becomes greater than maxReach, 
// it means we cannot even stand on that index. Since we can't reach the current position, we also can't use its jump length to move further. Therefore, reaching the 
// last index is impossible, so we immediately return false."

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// class Solution {
//     public boolean canJump(int[] nums) {
//         int goal = nums.length - 1;

//         for (int i = nums.length - 2; i >= 0; i--) {
//             if (i + nums[i] >= goal) {
//                 goal = i;
//             }
//         }
//         return goal == 0;        
//     }
// }




// Interview Explanation

// "I use a backward greedy approach. Initially, I consider the last index as my goal. Then I traverse the array from right to left. If the current index 
// can reach the goal (i + nums[i] >= goal), I move the goal to the current index. After processing all indices, if the goal becomes index 0,
// it means the first index can reach the last index. This approach runs in O(n) time and uses O(1) extra space."

// Interviewer:
// Why do you start from the second-last index instead of the last index?
// Best Answer (Interview)
// "The last index is already my initial goal, so there is no need to process it again. I only need to check whether the previous indices can reach this goal. 
// That's why I start from the second-last index (n - 2) and move backward until index 0."
