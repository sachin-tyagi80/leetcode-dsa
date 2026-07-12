class Solution {
    public int minimumAverageDifference(int[] nums) {

        long totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        long leftSum = 0;

        int answer = 0;
        long minDiff = Long.MAX_VALUE;

        int n = nums.length;

        for (int i = 0; i < n; i++) {

            leftSum += nums[i];

            long leftAvg = leftSum / (i + 1);

            long rightAvg = 0;

            if (i != n - 1) {
                rightAvg = (totalSum - leftSum) / (n - i - 1);
            }

            long diff = Math.abs(leftAvg - rightAvg);

            if (diff < minDiff) {
                minDiff = diff;
                answer = i;
            }
        }

        return answer;
    }
}








// "I use a Prefix Sum approach. First, I calculate the total sum of the array. Then I traverse the array while maintaining the prefix sum. 
// For each index, the left sum is the prefix sum, and the right sum is the total sum minus the prefix sum. Using these sums, 
// I compute the left and right averages with integer division, calculate their absolute difference,
// and keep track of the minimum difference and its index. This solution runs in O(n) time and O(1) extra space."
