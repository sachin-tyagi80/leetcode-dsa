class Solution {
    public int buyChoco(int[] prices, int money) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for(int price : prices){
            if(price < first){
                second = first;
                first = price;
            }else if(price < second){
                second = price;
            }
        }
        int cost = first + second;
        return (cost <= money) ? money - cost : money;
        
    }
}


// class Solution {

//     public int buyChoco(int[] prices, int money) {

//         Arrays.sort(prices);

//         int cost = prices[0] + prices[1];

//         if (cost <= money) {
//             return money - cost;
//         }

//         return money;
//     }
// }



// Interview Explanation (English)

// "To maximize the remaining money, I should minimize the amount spent. Therefore, I choose the two cheapest chocolates. I can either sort the array and take the
// first two elements or find the smallest and second smallest prices in one pass. If their total cost is within the available money, I return the remaining amount;
// otherwise, I return the original money."

// Common Interview Questions
// 1. Why choose the two cheapest chocolates?

// Because minimizing the purchase cost maximizes the leftover money.

// 2. Can we solve it without sorting?

// Yes. We only need the smallest and second smallest prices, so one linear scan is enough.

// 3. Which approach is better?
// Sorting: Simpler, O(n log n)
// One-pass Greedy: More efficient, O(n)
