class Solution {
    public boolean sumGame(String num) {

        int n = num.length();

        int leftSum = 0;
        int rightSum = 0;

        int leftQ = 0;
        int rightQ = 0;

        // Left half
        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }

        // Right half
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                rightQ++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }

        int diff = rightSum - leftSum;
        int qDiff = leftQ - rightQ;

        // Odd number of ? difference
        // means Alice can force unequal sums
        if (qDiff % 2 != 0) {
            return true;
        }

        // Bob can force equality
        if (diff == (qDiff / 2) * 9) {
            return false;
        }

        // Otherwise Alice wins
        return true;
    }
}


// Interview Explanation + Follow-up Questions
// 1. Interview me question ko kaise explain karein?

// English:

// "We have an even-length string divided into two halves. Alice and Bob alternately replace ? with digits from 0 to 9. Bob wins if the final sums of both halves are equal, otherwise Alice wins. Since both players play optimally, we don't need to simulate every move. We only need the current sum difference and the difference in the number of question marks."

// Hindi:

// "String ko do halves me divide karte hain. Dono players ? ko 0 se 9 ke kisi digit se replace kar sakte hain. Bob ko dono halves ka sum equal karna hai, jabki Alice ko unequal rakhna hai. Optimal play ki wajah se hum individual moves simulate nahi karte; sirf sum difference aur ? ka difference calculate karte hain."

// 2. Main Greedy Observation ⭐

// Hum calculate karte hain:

// diff = rightSum - leftSum;
// qDiff = leftQ - rightQ;

// Agar qDiff odd hai:

// Alice wins

// because Alice/Bob ke turns ki wajah se extra ? ka advantage perfectly cancel nahi ho sakta.

// Agar qDiff even hai, Bob equality tab force kar sakta hai jab:

// diff == (qDiff / 2) * 9

// Then:

// return false;

// Otherwise:

// return true;
// 3. Interview me Code Explain
// int diff = rightSum - leftSum;
// int qDiff = leftQ - rightQ;

// Interviewer: Why these two variables?

// Answer:

// "diff represents the current difference between the two halves, while qDiff represents how many more question marks are available on the left compared to the right."

// This condition:
// if (qDiff % 2 != 0) {
//     return true;
// }

// Answer:

// "If the difference in question marks is odd, the turns cannot be perfectly paired, so Alice can force the final sums to be different."

// This condition:
// if (diff == (qDiff / 2) * 9) {
//     return false;
// }

// Answer:

// "Each unmatched pair of question marks can create a maximum difference of 9. If the existing sum difference exactly matches the required compensation, Bob can force equality."

// 4. Follow-up Questions
// Q1. Why don't we simulate Alice and Bob's moves?

// Answer:

// "There are up to 10^5 characters, and each ? has 10 possible digits. Simulating all possibilities would be exponential. The greedy mathematical observation lets us determine the winner in O(n)."

// Q2. Why is 9 used?

// Because a ? can become:

// 0,1,2,...,9

// The maximum possible difference between two choices is:

// 9 - 0 = 9
// Q3. Why does an odd qDiff mean Alice wins?

// Because Alice and Bob alternate turns. If one side has an odd number of extra ?, the moves cannot be perfectly paired between the two halves.

// Alice can exploit that unmatched move to prevent equality.

// Q4. Why rightSum - leftSum?

// Direction itself isn't important, but diff and qDiff must use the correct corresponding signs.

// We use:

// diff = rightSum - leftSum;
// qDiff = leftQ - rightQ;

// Then:

// diff == (qDiff / 2) * 9

// is the balancing condition.

// Q5. What is the complexity?
// Time:  O(n)
// Space: O(1)

// We scan the string only once.

// Q6. Is this really Greedy?

// Answer:

// "Yes, the solution uses a greedy game-theory observation rather than simulating the game. We determine whether the remaining question marks can be optimally used to compensate for the current sum difference."

// ⭐ 30-Second Interview Answer

// "I divide the string into two halves and calculate the fixed digit sums and the number of ? in each half. Let diff = rightSum - leftSum and qDiff = leftQ - rightQ. If qDiff is odd, Alice can force unequal sums. Otherwise, Bob can force equality only when diff == (qDiff / 2) * 9. If that condition holds I return false, otherwise true. This gives O(n) time and O(1) space."
