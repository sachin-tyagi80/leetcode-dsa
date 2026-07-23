class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n]; // Intially everone gets one candy
        Arrays.fill(candies,1);
         
        for(int i=1;i<n;i++){ // left to right
            if(ratings[i]>ratings[i-1]){
                candies[i] = candies[i-1] + 1;
            }
        }
        for(int i= n-2;i>=0;i--){ //right to left
            if(ratings[i]>ratings[i+1]){
                candies[i] = Math.max(candies[i],candies[i+1]+1);
            }
        }
        int total =0;
        for(int candy : candies){
            total += candy;
        }

        return total;

    }
}



// 🎤 Interview Explanation (English) (30 sec)

// "I solved this problem using a Greedy two-pass approach. Initially every child receives one candy. In the first pass from left to right,
// I ensure that any child with a higher rating than the left neighbor gets one more candy. In the second pass from right to left, 
// I ensure the same condition for the right neighbor by taking the maximum of the current value and the required value. Finally, I sum all the candies.
// This guarantees the minimum valid distribution."


///////////////////////////////////////////////////////////////////////
// Q: Why can't we solve it in a single pass?
// English: Because while satisfying the left neighbor, we may violate the right neighbor condition. Two passes ensure both constraints are satisfied independently.
// Hindi: Kyunki ek hi traversal me left condition satisfy karte waqt right condition toot sakti hai. Isliye ek pass left ke liye aur ek pass right ke liye karte hain.
// Dono milkar minimum valid answer dete hain.

/////////////////////////////////////////////////////////////////////////////////////
// "A single left-to-right pass only ensures that every child has more candies than the left neighbor when required. It completely ignores situations where a
// child must have more candies than the right neighbor. For example, in ratings [3,2,1], the left pass gives [1,1,1], which violates both neighbor conditions. 
// Therefore, a second pass from right to left is necessary."
