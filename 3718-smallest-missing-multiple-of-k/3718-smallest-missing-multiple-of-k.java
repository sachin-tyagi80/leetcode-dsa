class Solution {
    public int missingMultiple(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();

        // Store all numbers
        for(int num : nums){
            set.add(num);
        }

        // check multiple of k 
        int multiple = k;

        while(set.contains(multiple)){
            multiple += k;
        }

        return multiple;
        
    }
}