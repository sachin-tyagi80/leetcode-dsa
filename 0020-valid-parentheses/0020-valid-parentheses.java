class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()){
            if(c == '('){
                stack.push(')');
            }
            else if(c == '{'){
                stack.push('}');
            }
            else if(c == '['){
                stack.push(']');
            }
            else if(stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }

        return stack.isEmpty();
        
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 🎯 Interview Explanation — Best Version

// Interviewer bole:

// "Explain your approach."

// Tum bolo:

// "I use a stack because brackets need to be matched in LIFO order. Instead of storing opening brackets, I store the expected closing bracket. When I see (, I push ). When I see {, I push }, and when I see [, I push ]. For every closing bracket, I check whether the stack is empty or whether the top element is different from the current character. If either condition is true, I return false. Finally, the stack must be empty because every opening bracket should have been closed."

// Then complexity:

// "Each character is processed once, so the time complexity is O(n). In the worst case, all characters can be opening brackets, so the space complexity is O(n)."

// 🔥 Interview Follow-up Questions
// Q1. Why Stack?

// Because brackets follow LIFO.

// Example:

// ([{}])

// Opening order:

// (
// [
// {

// Closing order:

// }
// ]
// )

// Jo last open hua {, woh first close hua }.

// Exactly Stack ka behavior.

// Q2. Why can't we use a simple counter?

// Counter sirf quantity track karega.

// Example:

// ([)]

// Opening aur closing brackets ki quantity equal ho sakti hai, but order wrong hai.

// Stack order bhi check karta hai.

// Q3. What happens if string is "((("?

// Processing ke baad:

// stack = )))

// Stack empty nahi hai.

// Therefore:

// return stack.isEmpty();

// returns:

// false
// Q4. What happens if string is ")"?

// Starting mein Stack empty hai.

// Condition:

// stack.isEmpty()

// true.

// So:

// false
// Q5. Why do we check stack.isEmpty() before pop()?

// Because empty Stack se pop() karenge to exception aa sakta hai.

// So:

// stack.isEmpty() || stack.pop() != c

// pehle empty check karta hai.

// Java || short-circuit evaluation use karta hai, so agar Stack empty hai, stack.pop() execute nahi hoga.

// Q6. Why stack.pop() != c?

// Because opening bracket ke time hum already expected closing bracket store kar chuke hain.

// Example:

// ( → push )

// Later:

// c = ')'

// Then:

// stack.pop() = ')'
// c = ')'

// Match → valid.

// Q7. What if input is "([]"?

// Processing:

// ( → push )
// [ → push ]

// End mein:

// stack = ])

// Stack empty nahi hai.

// Therefore:

// false
// Q8. What is the maximum size of Stack?

// Worst case:

// "((((((("

// All characters opening brackets hain.

// So stack size = n.

// Therefore space:

// O(n)
// Q9. Can we use ArrayDeque instead of Stack in Java?

// Yes. Modern Java mein ArrayDeque generally preferred hai.

// Deque<Character> stack = new ArrayDeque<>();

// Then:

// stack.push(')');
// stack.pop();
// stack.isEmpty();

// Same logic.

// But LeetCode/interview ke basic explanation ke liye Stack<Character> perfectly understandable hai.

// ⭐ Final Pattern Yaad Rakho

// Is question ka shortcut:

// Opening bracket
//       ↓
// Push expected closing bracket
//       ↓
// Closing bracket
//       ↓
// Stack empty? → false
//       ↓
// Top != current? → false
//       ↓
// Pop
//       ↓
// End → Stack empty? → true

// Ye Stack ka very important pattern hai:
// "OPEN → PUSH EXPECTED CLOSE, CLOSE → POP & MATCH."
