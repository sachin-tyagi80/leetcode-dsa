class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
        
    }
}


// Interview Question
// Why is the answer always true?
// Because:
// Number of piles is even.
// Alice moves first.
// She can decide whether to collect all even-indexed piles or all odd-indexed piles.
// She compares both sums before the game.
// She always follows the larger parity.
// Since total sum is odd, the two parity sums cannot be equal.
// Therefore Alice is guaranteed to collect more stones than Bob.

// Interview Explanation (20–30 sec)

// "Although this problem is tagged as Dynamic Programming, under the given constraints the answer is always true. Since the number of piles is even,
// Alice can choose either the even-indexed piles or the odd-indexed piles by selecting the appropriate end on her first move. 
// She compares the total stones in both parity groups and commits to the larger one. Because the total number of stones is odd, 
// the two sums cannot be equal, so Alice is guaranteed to collect more stones than Bob."
