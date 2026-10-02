class Solution {
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();

        backtrack("", 0, 0, n, result);

        return result;
    }

    private void backtrack(
        String current,
        int open,
        int close,
        int n,
        List<String> result
    ) {

        // String complete
        if (open == n && close == n) {
            result.add(current);
            return;
        }

        // Add opening bracket
        if (open < n) {
            backtrack(
                current + "(",
                open + 1,
                close,
                n,
                result
            );
        }

        // Add closing bracket only when valid
        if (close < open) {
            backtrack(
                current + ")",
                open,
                close + 1,
                n,
                result
            );
        }
    }
}

/////////////////////////////////////////////////////////////////////////////////////
// Interview mein kya bolna hai?

// "I will use backtracking to generate only valid parenthesis strings. I maintain two counters: the number of opening and closing brackets used. I can add an opening bracket while open < n. I can add a closing bracket only when close < open, because we cannot close a bracket that hasn't been opened. When both open and close become equal to n, I add the current string to the result. This pruning prevents invalid combinations from being generated."

// Then complexity:

// "The number of valid combinations is the nth Catalan number, so the output size is exponential. The time complexity is O(Cₙ × n), and the auxiliary recursion space is O(n), excluding the output."

// 🔥 Interview Follow-up Questions
// Q1. Why close < open?

// Because closing bracket ke liye ek unmatched opening bracket available hona chahiye.

// open = 2
// close = 1

// Then ) allowed.

// But:

// open = 1
// close = 1

// Then another ) allowed nahi hai.

// Q2. Why can't we add ) when close == open?

// Because then closing brackets opening se zyada ho jayenge.

// Example:

// ()

// Already balanced hai.

// Another ):

// ())

// invalid.

// Q3. Why open < n?

// Because exactly n pairs chahiye.

// Agar:

// n = 3

// to total opening brackets exactly 3 honge.

// ((( 

// ke baad fourth ( allowed nahi hai.

// Q4. Is this DFS?

// Yes.

// Backtracking essentially DFS over a decision tree hai.

// Har position par choices hain:

// add '('
// add ')'

// Aur recursion tree ko depth-first traverse karte hain.

// Q5. Can we use a Stack?

// Technically yes, but Stack ki zarurat nahi hai.

// Hume actual brackets ko push/pop karke validate nahi karna.

// Counters:

// open
// close

// sufficient hain because hum valid strings generate kar rahe hain.

// Q6. What is the most important condition?
// if (close < open)

// Ye condition ensure karti hai ki generated string kabhi invalid prefix na banaye.

// Q7. n = 1 ka answer?
// ["()"]

// Because:

// open < 1 → (
// close < open → )

// Final:

// ()
// ⭐ One-line pattern yaad rakho
// Generate '(' → open < n
// Generate ')' → close < open
Complete → open == close == n

Ye question ka core Backtracking + Pruning hai.
