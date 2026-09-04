class Solution {

    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        // suffixMin[i] = minimum from i to n-1
        int[] suffixMin = new int[n];

        suffixMin[n - 1] = nums[n - 1];

        // Build suffix minimum
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Prefix maximum
        int prefixMax = Integer.MIN_VALUE;

        // Check every index
        for (int i = 0; i < n; i++) {

            prefixMax = Math.max(prefixMax, nums[i]);

            int instability = prefixMax - suffixMin[i];

            // First stable index
            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}