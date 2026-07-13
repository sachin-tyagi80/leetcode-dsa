class Solution {
    public int maxProfit(int[] prices) {
        int buy = 0, sell = 1;
        int maxProfit = 0;
        
        while(sell < prices.length){
            if(prices[buy] < prices[sell]){
                maxProfit = Math.max(maxProfit,prices[sell] - prices[buy]);
            }
            else{
                buy = sell;
            }
            sell++;

        }
        return maxProfit;
    }
}


// "I solve this problem using the Two Pointers approach. One pointer represents the buying day, and the other represents the selling day. 
// If the selling price is greater than the buying price, I calculate the profit and update the maximum profit. 
// If I find a lower price, I move the buying pointer to that day because buying at a lower price can only increase future profit.
// Since each pointer moves only forward, the solution runs in O(n) time and O(1) extra space."    
////////////////////////////////////////////////////////////////////////////////////////////////////////////
// class Solution {

//     public int maxProfit(int[] prices) {

//         int minPrice = Integer.MAX_VALUE;
//         int maxProfit = 0;

//         for (int price : prices) {

//             minPrice = Math.min(minPrice, price);

//             maxProfit = Math.max(maxProfit, price - minPrice);
//         }

//         return maxProfit;
//     }
// }


// 🎤 Interview Explanation – Greedy (Optimal) ⭐⭐⭐
// "Finally, I optimized the DP solution into a Greedy approach. The key observation is that I don't need the entire DP array because only the minimum 
// buying price and the maximum profit matter. While traversing the array once, I continuously update the minimum price seen so far. 
// Then I calculate the profit if I sell on the current day and update the maximum profit whenever I find a better value. 
// This solves the problem in a single pass with O(n) time and O(1) extra space, making it the most efficient solution."
/////////////////////////////////////////////////////////////////////////////////

// 🎤 Interview Explanation – Brute Force

// "My first approach is Brute Force. I consider every day as a possible buying day and compare it with every future day as a selling day. 
// For each pair, I calculate the profit and keep track of the maximum profit. Since every possible buy-sell combination is checked, 
// the time complexity is O(n²) and space complexity is O(1). This approach is simple but inefficient for large inputs."

