class Solution {
    public boolean uniformArray(int[] nums1) {
        int minimum = Integer.MAX_VALUE;
        boolean allEven = true;

        for (int num : nums1) {
            minimum = Math.min(minimum, num);

            if (num % 2 == 1) {
                allEven = false;
            }
        }

        return allEven || minimum % 2 == 1;
    }
}



// 🎤 Interview Explanation

// Interviewer: Approach explain karo.

// You:

// “First, I find the minimum element and simultaneously check whether all elements are even.

// If all elements are already even, we can directly choose every element, so the answer is true.

// Otherwise, there is at least one odd element. In that case, the minimum element must be odd. If the minimum is odd, we can subtract this minimum from an even element, which makes the result odd. So we can make all elements odd.

// If the minimum is even and there is an odd element, then it is impossible to make all elements have the same parity.

// Therefore, the answer is allEven || minimum % 2 == 1.”

// 🔍 Example 1: [1, 4, 7]
// minimum = 1
// allEven = false

// Minimum odd hai.

// 1 → 1       odd
// 4 → 4 - 1   = 3 odd
// 7 → 7       odd

// Sab odd → true ✅

// 🔍 Example 2: [2, 3]
// minimum = 2
// allEven = false

// Minimum even hai aur 3 odd hai.

// 2 → 2       even
// 3 → 3 - 2   = 1 odd

// Same parity possible nahi → false ❌

// Follow-up Questions
// Q1. Minimum element important kyu hai?

// Answer:

// “Minimum element ke paas koi smaller element nahi hai, so subtraction option use nahi kar sakta. Therefore, its original parity cannot be changed.”

// Q2. Agar minimum odd hai to answer true kyu?

// Answer:

// “Minimum odd ko directly choose kar sakte hain. Any even number minus this odd minimum becomes odd. So we can make all elements odd.”

// Example:

// 8 - 3 = 5

// Both 3 and 5 odd.

// Q3. Agar minimum even aur koi odd element ho?

// Answer:

// “Then answer false, because the minimum must remain even, and an odd element cannot become even by subtracting the minimum even value.”

// Example:

// [2, 3]

// 3 - 2 = 1

// Still odd.

// Q4. allEven true hone par minimum check kyu nahi karna?

// Answer:

// “If every element is already even, we can simply choose Option 1 for every element. So no subtraction is needed.”

// Example:

// [2, 4, 6]

// Directly:

// nums2 = [2, 4, 6]

// All even → true.

// Q5. Kya allOdd variable bhi bana sakte hain?

// Haan, but zarurat nahi hai.

// Because agar all elements odd hain, then minimum automatically odd hoga.

// So:

// return allEven || minimum % 2 == 1;

// is enough.

// Q6. Time & Space Complexity?

// Time: O(n) — ek hi traversal.

// Space: O(1) — sirf minimum aur allEven variables.
