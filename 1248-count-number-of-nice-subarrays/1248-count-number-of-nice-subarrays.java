class Solution {

    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {

        int left = 0;
        int oddCount = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {

            // Current number odd hai
            if (nums[right] % 2 != 0) {
                oddCount++;
            }

            // Window me k se zyada odd ho gaye
            while (oddCount > k) {

                if (nums[left] % 2 != 0) {
                    oddCount--;
                }

                left++;
            }

            // [left ... right] se banne wale
            // saare subarrays ke odd <= k hain
            count += right - left + 1;
        }

        return count;
    }
}