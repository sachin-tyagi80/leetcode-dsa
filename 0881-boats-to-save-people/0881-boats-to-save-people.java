class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people); // sort weights
        int l=0;
        int r= people.length-1;
        int b = 0;
        while(l<=r){
            if(people[l] + people[r] <= limit){  // if lightest and heaviest can share a boat
                l++;
            }
            r--; // Heaviest person always goes
            b++;  // one boat is uesd
        }
        return b;
    }
}


//////////////////////////////////////////////////////////////////////////////
// Why do we always send the heaviest person?
// Because the heaviest person cannot wait for a better partner later.
// If the lightest person can't fit with the heaviest:
// lightest + heaviest > limit
// then no one else can fit with the heaviest either (everyone else is heavier than the lightest).
// So the heaviest person must go alone.

// Interview Questions
// Q1. Why sorting?
// Sorting lets us efficiently pair the lightest and heaviest people using two pointers.

// Q2. Why pair the lightest with the heaviest?
// If the heaviest person can share a boat, pairing them with the lightest leaves more room for other people later, minimizing the total number of boats.

// Q3. Why does the heaviest go alone if pairing fails?
// If the lightest person cannot fit with the heaviest, then no other person can fit with the heaviest because everyone else is heavier.

// Interview Explanation (25–30 sec)
// "I use a greedy approach with sorting and two pointers. After sorting the weights, I place one pointer at the lightest person and another at the heaviest.
// If their combined weight is within the limit, they share a boat and both pointers move. Otherwise, the heaviest person must go alone because they cannot pair with anyone if they cannot pair with the lightest person. 
// Each iteration uses one boat, giving an O(n log n) solution due to sorting."

// Complexity
// Time: O(n log n) (sorting)
// Space: O(1) (excluding sorting implementation)
