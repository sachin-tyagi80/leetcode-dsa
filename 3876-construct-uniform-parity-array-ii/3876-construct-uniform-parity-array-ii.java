class Solution {
    public boolean uniformArray(int[] nums1) {

        int min = nums1[0];

        // Find minimum element
        for (int num : nums1) {
            min = Math.min(min, num);
        }

        // Minimum element decides the target parity
        int target = min % 2;

        for (int num : nums1) {

            // Option 1: use num directly
            if (num % 2 == target) {
                continue;
            }

            // Option 2: subtract minimum
            int value = num - min;

            // Check if result is valid and has target parity
            if (value < 1 || value % 2 != target) {
                return false;
            }
        }

        return true;
    }
}