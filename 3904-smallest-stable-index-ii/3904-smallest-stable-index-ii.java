class Solution {

    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        // suffixMin[i] = minimum element from i to n-1
        int[] suffixMin = new int[n];

        // Last element
        suffixMin[n - 1] = nums[n - 1];

        // Build suffix minimum array
        for (int i = n - 2; i >= 0; i--) {

            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Maximum from 0 to current index
        int prefixMax = Integer.MIN_VALUE;

        // Find smallest stable index
        for (int i = 0; i < n; i++) {

            // Update prefix maximum
            prefixMax = Math.max(prefixMax, nums[i]);

            // Calculate instability
            long instability = (long) prefixMax - suffixMin[i];

            // Stable index
            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}