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