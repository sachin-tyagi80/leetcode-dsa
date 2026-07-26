class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1001];
        for(int[] trip : trips){
            int passengers = trip[0];
            int start = trip[1];
            int end = trip[2];

            diff[start] += passengers;
            diff[end] -= passengers;
        }
        int curr = 0;
        for(int i=0;i<=1000;i++){
            curr += diff[i];

            if(curr > capacity){
                return false;
            }
        }
        return true;
        
    }
}

//Difference Array + Prefix Sum ---------->>>> O(n + 1000)


// Interview Explanation (English)
// "I used a Difference Array and Prefix Sum approach. For every trip, I increase the passenger count at the pickup location and decrease it at the drop location.
// Then I compute the prefix sum to determine the number of passengers inside the car at every location. If the passenger count exceeds the capacity at any point, 
// I return false; otherwise, I return true."

//////////////////////////////////////////////////////////////////////
// Interview Follow-up Questions
// Q1. Why do we subtract passengers at to?
// English: Passengers leave the car at the drop location, so they should no longer be counted from that point onward.
// Hindi: to location par passengers utar jaate hain, isliye wahi se unhe count nahi karna chahiye.

// Q2. Why use Prefix Sum?
// English: The Difference Array stores only changes. Prefix Sum converts those changes into the actual passenger count.
// Hindi: Difference Array sirf changes store karta hai. Prefix Sum un changes ko actual passengers me convert karta hai.

// Q3. Why is this better than checking every location?
// English: We process each trip only once and then make one pass over the locations, making the solution much more efficient.
// Hindi: Har trip ko sirf ek baar process karte hain aur locations par sirf ek traversal hota hai. Isliye ye Brute Force se kaafi fast hai.
