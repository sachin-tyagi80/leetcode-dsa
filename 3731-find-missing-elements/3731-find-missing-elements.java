class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        int curr = nums[0];
        int n = nums.length;
        for(int i=0;i<n;curr++,i++){
            if(curr<nums[i]){
                res.add(curr);
                i--;
            }
        }
        return res;
        
    }
}