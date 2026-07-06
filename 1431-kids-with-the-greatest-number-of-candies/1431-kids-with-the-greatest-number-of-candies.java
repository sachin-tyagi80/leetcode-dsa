class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int m =0;
        for(int candy : candies){
            m = Math.max(m ,candy);
        }
        List<Boolean> ans = new ArrayList<>();
        for(int candy : candies){
            if(candy + extraCandies >= m){
                ans.add(true);
            }else{
                ans.add(false);
            }
        }
        return ans;
        
    }
}