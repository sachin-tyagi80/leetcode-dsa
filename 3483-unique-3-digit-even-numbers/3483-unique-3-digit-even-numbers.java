import java.util.*;

class Solution {
    public int totalNumbers(int[] digits) {

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < digits.length; i++) {
            for (int j = 0; j < digits.length; j++) {
                for (int k = 0; k < digits.length; k++) {

                    if (i == j || j == k || i == k) {
                        continue;
                    }

                    if (digits[i] == 0) {
                        continue;
                    }

                    if (digits[k] % 2 != 0) {
                        continue;
                    }

                    int num = digits[i] * 100
                            + digits[j] * 10
                            + digits[k];

                    set.add(num);
                }
            }
        }

        return set.size();
    }
}


// Code ka interview explanation

// Interview me directly ye bol sakte ho:

// "I need to generate all possible three-digit numbers using three different indices. I reject cases where the first digit is zero because that would not be a three-digit number. I also require the last digit to be even. Since duplicate digits can generate the same number, I store every valid number in a HashSet. Finally, the size of the set gives the number of distinct valid numbers."

// Hindi me

// "Main teen loops se hundreds, tens aur units digit choose kar raha hoon. First digit zero hua to skip kar raha hoon, aur last digit odd hua to skip kar raha hoon. Same number duplicate na aaye, isliye HashSet use kiya hai. End me HashSet ka size answer hai."

// 3. Dry Run

// Let's take:

// digits = [1, 2, 3, 4]
// i = 0

// Hundreds = 1

// Tens aur units choose karenge.

// 124 ✅
// 123 ❌ last digit odd
// 134 ✅
// 132 ✅

// Similarly:

// 142
// 214
// 234
// ...

// Valid distinct numbers:

// 124
// 132
// 134
// 142
// 214
// 234
// 312
// 314
// 324
// 342
// 412
// 432

// Total:

// 12
// 4. Duplicate digits ka important case

// Suppose:

// digits = [0, 2, 2]

// Loops same 2 ko different indices se choose kar sakte hain.

// For example:

// i = 2
// j = 0
// k = 1

// => 220

// Another index combination se bhi 220 ban sakta hai.

// But:

// set.add(220);

// HashSet duplicate ko automatically remove kar deta hai.

// So:

// Set = {202, 220}

// Answer:

// 2
// 5. Why i == j etc.?

// Ye bahut important interview point hai.

// Suppose:

// digits = [1, 2, 3]

// Hum same array element ko multiple times use nahi kar sakte.

// Therefore:

// if (i == j || j == k || i == k)

// ka matlab hai:

// Same index ko ek number ke andar dobara use mat karo.

// For example:

// i = 0
// j = 0
// k = 1

// Invalid hai because digit 1 ki sirf ek copy hai.

// 6. Why HashSet?

// Suppose:

// digits = [2, 2, 4]

// Number 224 multiple index combinations se ban sakta hai.

// Agar simple count++ karenge:

// 224
// 224

// to wrong answer milega.

// HashSet:

// set.add(num);

// sirf unique numbers rakhta hai.

// Therefore:

// return set.size();

// correct answer deta hai.

// 7. Complexity

// Let n = digits.length.

// Three loops:

// O(n³)

// Since:

// n <= 10

// maximum iterations:

// 10 × 10 × 10 = 1000

// So this approach is very fast.

// Space:

// O(number of distinct 3-digit numbers)

// Maximum 900 possible 3-digit even numbers hote hain, so practically very small.

// 🎯 Important Interview Follow-up Questions
// Q1. Why can't we simply use permutations?

// Because duplicate digits can produce duplicate numbers.

// Example:

// [2, 2, 4]

// Different index permutations can produce the same number.

// So we need either:

// HashSet, or
// frequency counting.
// Q2. Why must the last digit be even?

// A decimal number is even iff its last digit is one of:

// 0, 2, 4, 6, 8

// That's why:

// if (digits[k] % 2 != 0)
//     continue;
// Q3. Why can't the first digit be zero?

// Because:

// 023

// is actually:

// 23

// which is a 2-digit number, not a 3-digit number.

// Hence:

// if (digits[i] == 0)
//     continue;
// Q4. Can we use the same digit twice?

// Only if there are multiple copies.

// Example:

// digits = [2, 2, 0]

// 220 is valid.

// But:

// digits = [2, 0, 1]

// 222 is invalid because only one 2 exists.

// Q5. What if all digits are odd?

// Example:

// [1, 3, 5]

// No possible even ending digit.

// Answer:

// 0
// Q6. What if 0 is the only even digit?

// Example:

// [1, 2, 3, 0]

// Numbers can end in 0, but 0 cannot be the first digit.

// For example:

// 120 ✅
// 130 ✅
// 210 ✅
// 310 ✅
// Q7. Why is brute force acceptable here?

// Because:

// n <= 10

// and three loops give only:

// 10³ = 1000

// iterations.

// So O(n³) is completely acceptable.

// ⭐ Interview takeaway

// Is problem me interviewer mainly ye concepts check kar sakta hai:

// 3-digit number
//       ↓
// First digit ≠ 0
//       ↓
// Last digit must be even
//       ↓
// Each index can be used once
//       ↓
// Duplicate numbers?
//       ↓
// HashSet

// One-line interview summary:

// "I enumerate all choices for hundreds, tens and units digits, reject leading-zero and odd-ending numbers, ensure indices are not reused, and use a HashSet to count only distinct valid numbers."

// Approach 2: Frequency Array — Better Approach

// Interview me ek aur cleaner approach dikha sakte ho.

// Since digits sirf 0–9 hain, frequency array bana sakte hain:

// int[] freq = new int[10];

// Then har possible 3-digit number ko generate karo:

// 100 → 998

// and check karo ki uske digits available hain.

// Java
// class Solution {
//     public int totalNumbers(int[] digits) {

//         int[] freq = new int[10];

//         for (int digit : digits) {
//             freq[digit]++;
//         }

//         int count = 0;

//         for (int num = 100; num <= 998; num++) {

//             if (num % 2 != 0) {
//                 continue;
//             }

//             int a = num / 100;
//             int b = (num / 10) % 10;
//             int c = num % 10;

//             int[] need = new int[10];

//             need[a]++;
//             need[b]++;
//             need[c]++;

//             boolean possible = true;

//             for (int d = 0; d <= 9; d++) {
//                 if (need[d] > freq[d]) {
//                     possible = false;
//                     break;
//                 }
//             }

//             if (possible) {
//                 count++;
//             }
//         }

//         return count;
//     }
// }
// Which approach should you use?
