class Solution {
    public int smallestNumber(int n, int t) {
        while(true){
            int product = 1;
            int num = n;

            while(num>0){  // Calculate digit product
                product *= (num%10);
                num /= 10;

            }

            if(product % t == 0){  // Check divisibility
                return n;
            }
            n++;
        }
        
    }
}


// Interview Questions
// Q1. Why use brute force?
// Because the constraints are very small (n ≤ 100), making enumeration efficient.

// Q2. Why initialize product = 1?
// Since we're multiplying digits, 1 is the multiplicative identity.
// Starting with 0 would always make the product 0.

// Q3. What happens if a digit is 0?
// The product becomes 0.
// Since 0 is divisible by every positive integer,
// 0 % t = 0
// the condition is automatically satisfied.

// Q4. Time Complexity
// Let k be the number of numbers checked.
// Each number has at most 3 digits.
// O(k × digits)
// Since digits ≤ 3,
// Overall is approximately
// O(k)
    
// Q5. Space Complexity
// O(1)
// No extra data structures are used.

// Interview Explanation (30 seconds)
// "I use a brute-force enumeration approach because the constraints are very small. Starting from n, I calculate the product of the digits for each number.
// If the product is divisible by t, I return that number. Otherwise, I increment the number and repeat. Since n is at most 100 and each number has only a 
// few digits, this approach is efficient with constant extra space."
