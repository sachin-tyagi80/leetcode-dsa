class Solution {

    public int maxIceCream(int[] costs, int coins) {

        int maxCost = 0;

        // Find maximum cost
        for (int cost : costs) {
            maxCost = Math.max(maxCost, cost);
        }

        // Frequency array
        int[] freq = new int[maxCost + 1];

        for (int cost : costs) {
            freq[cost]++;
        }

        int count = 0;

        // Buy cheapest ice creams first
        for (int price = 1; price <= maxCost; price++) {

            while (freq[price] > 0 && coins >= price) {

                coins -= price;
                count++;
                freq[price]--;
            }
        }

        return count;
    }
}


// Interview Explanation (English)

// "To maximize the number of ice creams, I always buy the cheapest available ice cream first. This is a greedy strategy because buying cheaper items leaves more coins to purchase additional ice creams. Since the problem expects counting sort, I first count the frequency of each price and then iterate from the smallest price to the largest while purchasing as many ice creams as possible."

// Interview Questions
// Q1. Why Greedy?

// Because buying the cheapest ice cream first always leaves the maximum money for future purchases.

// Q2. Why sort?

// Sorting arranges prices from smallest to largest, allowing us to buy the cheapest first.

// Q3. Why Counting Sort instead of Arrays.sort()?

// Because prices are bounded (costs[i] ≤ 10^5), counting sort achieves O(n + maxCost) time, which is faster than O(n log n) sorting.

/////////////////////////////////////////////////
// class Solution {

//     public int maxIceCream(int[] costs, int coins) {

//         Arrays.sort(costs);

//         int count = 0;

//         for (int cost : costs) {

//             if (cost <= coins) {

//                 coins -= cost;
//                 count++;

//             } else {

//                 break;
//             }
//         }

//         return count;
//     }
// }


// Interview Explanation (20–30 seconds)

// "I solved this problem using a Greedy approach with Counting Sort. Instead of sorting the array, I first count the frequency of each ice cream price because the problem specifically asks for counting sort. Then I iterate from the cheapest price to the most expensive price. As long as I have enough coins, I buy that ice cream, reduce the remaining coins, and increase my answer. Buying the cheapest ice creams first is always optimal because it maximizes the total number of ice creams I can purchase."

// If the interviewer asks "Why Counting Sort instead of Arrays.sort()?"

// "The problem specifically requires Counting Sort. Since the price range is limited (costs[i] ≤ 10^5), I can store the frequency of each price in an array and process prices in increasing order. This gives O(n + maxCost) time complexity, which is better than the O(n log n) complexity of comparison-based sorting."

// If the interviewer asks "Why is the Greedy approach correct?"

// "The objective is to maximize the number of ice creams, not minimize the money left. Therefore, buying the cheapest available ice cream first always leaves the maximum number of coins for future purchases, allowing us to buy more ice creams. Choosing a more expensive ice cream first could reduce the total number of ice creams we can buy."

// Time Complexity
// Time: O(n + maxCost)
// Space: O(maxCost)





