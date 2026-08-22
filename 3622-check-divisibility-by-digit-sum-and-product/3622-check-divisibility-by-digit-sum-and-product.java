class Solution {

    public boolean checkDivisibility(int n) {

        // Store original number
        int original = n;

        // Sum of digits
        int sum = 0;

        // Product of digits
        int product = 1;

        // Extract every digit
        while (n > 0) {

            // Get last digit
            int digit = n % 10;

            // Add digit to sum
            sum += digit;

            // Multiply digit into product
            product *= digit;

            // Remove last digit
            n /= 10;
        }

        // Divisor = digit sum + digit product
        int divisor = sum + product;

        // Check divisibility
        return original % divisor == 0;
    }
}


// Interview Explanation ⭐

// Interviewer ko bolo:

// "I will extract each digit using n % 10. For every digit, I will add it to the digit sum and multiply it into the digit product. Then I will calculate sum + product and check whether the original number is divisible by this value using the modulo operator."

// 9. Complexity

// Agar n me d digits hain:

// Time = O(d)
// Space = O(1)

// Since n <= 10^6, maximum digits bahut small hain.

// 10. Interview Follow-up Questions
// Q1. Why do we use % 10?
// n % 10

// number ka last digit deta hai.

// Q2. Why do we use / 10?
// n /= 10;

// last digit remove karta hai.

// Q3. Why is product initialized to 1?

// Because:

// 1 × digit = digit

// Agar 0 se initialize karenge:

// 0 × anything = 0

// to product hamesha 0 ho jayega.

// Q4. Why do we store original?

// Because loop ke andar:

// n /= 10;

// kar rahe hain.

// End tak n = 0 ho jayega.

// Hume final divisibility check original number ke saath karna hai:

// original % divisor
// Q5. Can we solve it using String?

// Yes:

// String s = String.valueOf(n);

// Then each character ko digit me convert karke sum/product calculate kar sakte hain.

// But interview me mathematical approach better hai because % 10 and / 10 digit extraction clearly demonstrate karte hain.

// ⭐ Interview Pattern

// Is question ko dekhte hi:

// Number
//    ↓
// Extract digit
//    ↓
// n % 10
//    ↓
// sum += digit
// product *= digit
//    ↓
// n /= 10
//    ↓
// sum + product
//    ↓
// original % divisor == 0
// One-line trick:

// % 10 → digit nikalo, / 10 → digit hatao.
