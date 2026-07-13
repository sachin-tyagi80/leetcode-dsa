class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max  = 0;
        for(int price : prices){
            min = Math.min(min,price);
            max = Math.max(max,price-min);
        }
        return max;
        
    }
}


// 🎤 Interview Explanation – Greedy (Optimal) ⭐⭐⭐
// "Finally, I optimized the DP solution into a Greedy approach. The key observation is that I don't need the entire DP array because only 
// the minimum buying price and the maximum profit matter. While traversing the array once, I continuously update the minimum price seen so far.
// Then I calculate the profit if I sell on the current day and update the maximum profit whenever I find a better value. 
// This solves the problem in a single pass with O(n) time and O(1) extra space, making it the most efficient solution."



// class Solution {
//     public int maxProfit(int[] prices) {

//         int maxProfit = 0;

//         for (int i = 0; i < prices.length; i++) {

//             for (int j = i + 1; j < prices.length; j++) {

//                 maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
//             }
//         }

//         return maxProfit;
//     }
// }

// 🎤 Interview Explanation – Brute Force
// "My first approach is Brute Force. I consider every day as a possible buying day and compare it with every future day as a selling day. 
// For each pair, I calculate the profit and keep track of the maximum profit. Since every possible buy-sell combination is checked, 
// the time complexity is O(n²) and space complexity is O(1). This approach is simple but inefficient for large inputs."




