import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }

            map.put(nums[i], i);
        }

        return new int[0];
    }
}


// "I use a HashMap to store each number along with its index. For every element, 
// I calculate its complement (target - current). If the complement already exists in the map, 
// I return the stored index and the current index. Otherwise, I store the current number in the map. 
// This approach finds the answer in one pass with O(n) time complexity and O(n) extra space."
