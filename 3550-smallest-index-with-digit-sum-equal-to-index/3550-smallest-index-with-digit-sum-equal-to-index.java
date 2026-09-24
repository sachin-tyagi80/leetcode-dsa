class Solution {
    public int smallestIndex(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];
            int sum = 0;

            // Calculate digit sum
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }

            // Check digit sum == index
            if (sum == i) {
                return i;
            }
        }

        return -1;
    }
}


// /////////////////////////////////////////////////////////////////////////////
// 11. Interview mein kya bolna hai?

// Aap interviewer ko bol sakte ho:

// "I iterate through the array from left to right because we need the smallest index. For every element, I calculate its digit sum using modulo 10 and integer division by 10. If the digit sum equals the current index, I immediately return that index. If no index satisfies the condition, I return -1."

// Short version:

// "Traverse left to right, calculate digit sum for each number, and return the first index where digit sum equals the index."

// 12. Interview Follow-up Questions
// Q1. Why traverse from left to right?

// Because we need the smallest index.

// Q2. Why num % 10?

// It gives the last digit.

// 123 % 10 = 3
// Q3. Why num /= 10?

// It removes the last digit.

// 123 → 12 → 1 → 0
// Q4. What if no index satisfies the condition?

// Return:

// return -1;
// Q5. What if number is 0?

// Its digit sum is 0.

// Therefore if:

// index = 0
// nums[0] = 0

// then answer is 0.

// Q6. Can we stop early?

// Yes. Jaise hi:

// digitSum == i

// milta hai, immediately return kar sakte hain because array left-to-right traverse ho raha hai.

