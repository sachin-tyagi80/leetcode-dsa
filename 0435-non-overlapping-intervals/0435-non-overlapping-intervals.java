class Solution {
    public int eraseOverlapIntervals(int[][] nums) {
        int n = nums.length;
        Arrays.sort(nums, Comparator.comparingInt(o -> o[1]));
        int last  = nums[0][1];
        int count = 1;
        for(int i=1;i<n;i++){
            if(nums[i][0]>=last){
                count++;
                last = nums[i][1];

            }
        }
        return n-count;

        
    }
}

// "I solve this problem using the Greedy approach. First, I sort all intervals by their ending time because selecting the interval that finishes earliest leaves the maximum room for future intervals. Then I greedily select non-overlapping intervals by comparing the current interval's start time with the end time of the last selected interval. Finally, since the question asks for the minimum number of intervals to remove, I subtract the number of selected intervals from the total number of intervals."