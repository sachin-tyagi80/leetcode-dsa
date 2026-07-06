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



// **First, I find the maximum number of candies among all kids. Then, for each kid,
// I check whether candies[i] + extraCandies is greater than or equal to that maximum. 
// If yes, I add true to the result; otherwise, I add false. This requires two linear traversals, giving an overall time complexity of O(n).`
