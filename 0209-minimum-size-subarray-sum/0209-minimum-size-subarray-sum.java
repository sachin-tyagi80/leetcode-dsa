class Solution {

    public int minSubArrayLen(int target, int[] nums) {

        int left = 0;
        int sum = 0;

        int ans = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {

            // Add current element
            sum += nums[right];

            // Try to make the window smaller
            while (sum >= target) {

                // Current window length
                ans = Math.min(ans, right - left + 1);

                // Remove left element
                sum -= nums[left];

                // Move left pointer
                left++;
            }
        }

        // No valid subarray found
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}