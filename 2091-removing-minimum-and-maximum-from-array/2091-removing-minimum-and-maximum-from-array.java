class Solution {

    public int minimumDeletions(int[] nums) {

        int n = nums.length;

        // Find minimum and maximum values
        int min = nums[0];
        int max = nums[0];

        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 1; i < n; i++) {

            if (nums[i] < min) {
                min = nums[i];
                minIndex = i;
            }

            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }

        // left = smaller index
        // right = larger index
        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);

        // Case 1:
        // Remove both from the front
        int front = right + 1;

        // Case 2:
        // Remove both from the back
        int back = n - left;

        // Case 3:
        // Remove left target from front
        // and right target from back
        int both = (left + 1) + (n - right);

        // Return minimum of all three cases
        return Math.min(front, Math.min(back, both));
    }
}