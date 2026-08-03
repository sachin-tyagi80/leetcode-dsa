class Solution {
    public int maxScore(String s) {

        int ones = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '1') {
                ones++;
            }
        }

        int zeros = 0;
        int ans = 0;

        for (int i = 0; i < s.length() - 1; i++) {

            if (s.charAt(i) == '0') {
                zeros++;
            } else {
                ones--;
            }

            ans = Math.max(ans, zeros + ones);
        }

        return ans;
    }
}



// Why only till n-2?
// Because
// Question says
// Both substrings must be non-empty.
// If we split after the last character,
// 011101 |
// Right part becomes empty.
// Not allowed.

// So loop runs till
// i < s.length() - 1
// Interview Explanation (20–30 sec)
// "I use a greedy approach. First, I count the total number of ones in the string, assuming all of them are initially on the right side.
// Then I move the split from left to right. Whenever I encounter a zero, I increase the count of left zeros. Whenever I encounter a one,
// I decrease the count of right ones because that one moves to the left part. At every valid split, I calculate the score as leftZeros plus rightOnes and keep track of the maximum score."

// Interview Questions
// Q1. Why Greedy?
// Because we update the answer incrementally while moving the split once, avoiding repeated counting.

// Q2. Why stop at n-2?
// To ensure both left and right substrings remain non-empty.

// Q3. Can we use Brute Force?
// Yes.
// Try every split and count zeros and ones every time.
// Time Complexity
// O(n²)
// Q4. Optimized Complexity?
// Time: O(n)
// Space: O(1)

// At each move:

// If a 0 crosses to the left, leftZeros++.
// If a 1 crosses to the left, it leaves the right part, so rightOnes--.
