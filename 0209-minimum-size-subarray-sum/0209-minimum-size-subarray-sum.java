class Solution {

    public int minSubArrayLen(int target, int[] nums) {

        int left = 0;
        int sum = 0;

        int ans = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {

            // Add current element
            sum += nums[right];

            // Try to make the window smaller
            while (sum >= target) {

                // Current window length
                ans = Math.min(ans, right - left + 1);

                // Remove left element
                sum -= nums[left];

                // Move left pointer
                left++;
            }
        }

        // No valid subarray found
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}



// 13. Interview Explanation 🎤

// Agar interviewer kahe:

// Explain your approach.

// Tum ye bol sakte ho:

// "Since all elements in the array are positive, I can use a sliding window approach. I maintain two pointers, left and right, and a running sum. I expand the window by moving right and adding elements to the sum. Whenever the sum becomes greater than or equal to the target, I update the minimum length and shrink the window from the left while the condition remains valid. Because both pointers move only forward, each element is added and removed at most once, giving O(n) time and O(1) space."

// Simple Hindi meaning

// "Array mein saare numbers positive hain, isliye sliding window use kar sakte hain. Right pointer se window expand karenge aur sum maintain karenge. Jab sum target se greater ya equal ho jayega, answer update karenge aur left pointer se window ko shrink karenge. Dono pointers sirf forward move karte hain, isliye time complexity O(n) aur space O(1) hai."

// 14. Follow-up Questions Interviewer Can Ask
// Q1. Why do we need positive integers?

// Because sliding window relies on:

// expand → sum increases
// shrink → sum decreases

// If negative numbers are allowed, this property breaks.

// Q2. What if array contains negative numbers?

// This simple sliding-window solution does not work.

// We would need another technique, depending on the exact problem—for example, prefix sums with an appropriate data structure.

// Q3. Why do we use right - left + 1?

// Because both indices are inclusive.

// Example:

// left = 2
// right = 5

// Elements:

// 2, 3, 4, 5

// Number of elements:

// 5 - 2 + 1 = 4
// Q4. Why return 0?

// If no subarray has:

// sum >= target

// then problem says return 0.

// That's why:

// return ans == Integer.MAX_VALUE ? 0 : ans;
// Q5. Can we solve it using prefix sum?

// Yes.

// Prefix sum + binary search gives:

// O(n log n)

// which is the problem's follow-up.

// Q6. Can we use brute force?

// Yes:

// O(n²)

// But for n = 100000, it is too slow.

// Q7. Why is this problem called Sliding Window?

// Because the window:

// [left ........ right]

// moves through the array.

// It expands:

// left →     right →

// and shrinks:

//     left → 

// So it behaves like a window sliding over the array.

// 15. Important Pattern to Remember 🧠

// Is problem se ye Sliding Window template yaad rakho:

// int left = 0;
// int sum = 0;
// int ans = Integer.MAX_VALUE;

// for (int right = 0; right < nums.length; right++) {

//     sum += nums[right];

//     while (sum >= target) {

//         ans = Math.min(ans, right - left + 1);

//         sum -= nums[left];
//         left++;
//     }
// }

// return ans == Integer.MAX_VALUE ? 0 : ans;
// Mental formula:

// Expand → Check → Record → Shrink

// RIGHT++
//    ↓
// sum increase
//    ↓
// sum >= target ?
//    ↓
// answer update
//    ↓
// LEFT++
//    ↓
// window smaller

// Interview ke liye is problem ka main takeaway:
// Positive numbers + contiguous subarray + minimum/maximum length + sum condition → Sliding Window ko immediately consider karo.
