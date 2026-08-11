class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        
        while (set.contains(sum)) {
            sum++;
        }

        return sum;
        
    }
}


// “First, I find the longest sequential prefix by checking whether every next element is exactly one greater than the previous element. 
// I calculate the sum of this prefix. Then I put all array elements into a HashSet for O(1) average lookup. Starting from the prefix sum, 
// I keep increasing the value while it exists in the set. The first value not present in the set is the answer.”
