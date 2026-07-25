class Solution {

    public int jump(int[] nums) {

        int jumps = 0;

        // End of current jump range
        int end = 0;

        // Farthest index reachable
        int farthest = 0;

        // Traverse till second last index
        for (int i = 0; i < nums.length - 1; i++) {

            // Update farthest reachable index
            farthest = Math.max(farthest, i + nums[i]);

            // Current jump range is over
            if (i == end) {

                // Take one jump
                jumps++;

                // New jump range
                end = farthest;
            }
        }

        return jumps;
    }
}


// Interview Explanation

// "I solved this problem using a greedy approach. I treat each jump as a range of reachable indices. While traversing the current range,
// I continuously update the farthest index that can be reached. When I reach the end of the current range, I increment the jump count and extend the range
// to the farthest reachable index. This ensures that each jump covers the maximum possible distance, resulting in the minimum number of jumps."
