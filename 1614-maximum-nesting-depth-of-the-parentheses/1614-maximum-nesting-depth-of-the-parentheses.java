class Solution {
    public int maxDepth(String s) {
        int d =0;
        int maxDepth = 0;
        for(char c : s.toCharArray()){
            if(c == '('){
                d++;
                maxDepth = Math.max(maxDepth,d);
            }
            else{
                if(c == ')'){
                    d--;
                }
            }
        }
        return maxDepth;
    }
}

////////////////////////////////////////////////////////////////////////////////////
// 🎯 Interview mein kya bolna hai?

// "I can solve this using a simple counter instead of a stack. Whenever I encounter an opening parenthesis, I increment the current depth and update the maximum depth. Whenever I encounter a closing parenthesis, I decrement the depth. Since every character is processed once, the time complexity is O(n) and the space complexity is O(1)."

// 🔥 Interview Follow-up Questions
// 1. Stack use karke kar sakte ho?

// Yes, but unnecessary.

// Stack<Character> stack = new Stack<>();

// Opening bracket par push and closing bracket par pop kar sakte hain, but counter is more memory efficient.

// 2. maxDepth opening bracket ke baad hi update kyu kar rahe hain?

// Because depth sirf '(' par increase hoti hai.

// depth++;
// maxDepth = Math.max(maxDepth, depth);

// Closing bracket par depth decrease hoti hai, so maximum wahan nahi aa sakta.

// 3. Agar input valid na ho to?

// Is problem mein explicitly guarantee hai ki string VPS (Valid Parentheses String) hai.

// Isliye hume invalid cases handle karne ki zarurat nahi hai.

// 4. Kya digits/operators ko process karna zaroori hai?

// Nahi.

// Hume sirf:

// '('
// ')'

// matter karte hain.

// Baaki characters simply ignore ho jaate hain.
