class Solution {

    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {

        int left = 0;
        int oddCount = 0;
        int count = 0;

        for (int right = 0; right < nums.length; right++) {

            // Current number odd hai
            if (nums[right] % 2 != 0) {
                oddCount++;
            }

            // Window me k se zyada odd ho gaye
            while (oddCount > k) {

                if (nums[left] % 2 != 0) {
                    oddCount--;
                }

                left++;
            }

            // [left ... right] se banne wale
            // saare subarrays ke odd <= k hain
            count += right - left + 1;
        }

        return count;
    }
}


// 🧠 Interviewer ko aise bolna

// "I need to count subarrays having exactly k odd numbers. Counting exactly k directly using a sliding window is inconvenient, so I convert it into two at-most problems. I calculate the number of subarrays having at most k odd numbers and subtract the number having at most k-1 odd numbers.

// For the atMost function, I maintain a sliding window using left and right pointers. Whenever the number of odd elements exceeds k, I shrink the window from the left. Once the window becomes valid, all subarrays ending at right and starting from any index between left and right are valid, so I add right - left + 1 to the answer.

// Since each element enters and leaves the window at most once, the time complexity is O(n) and the space complexity is O(1)."

// Ye explanation interview ke liye kaafi strong hai.

// 🔥 Follow-up Questions Interviewer Pooch Sakta Hai
// Q1. Why not use a normal sliding window for exactly k?

// Answer:

// Exactly k maintain karna directly difficult hai because when window has exactly k odd numbers, there can be multiple possible starting positions producing valid subarrays.

// Isliye:

// Exactly K = AtMost(K) - AtMost(K-1)

// use karna easier hai.

// Q2. Why does right - left + 1 work?

// Answer:

// Because after shrinking, [left...right] is a valid window.

// Every subarray ending at right and starting from:

// left, left+1, ..., right

// will also have at most k odd numbers.

// Number of such starting positions:

// right - left + 1
// Q3. Why can we use sliding window here?

// Answer:

// Because array elements are positive in the sense that we are counting a property—number of odd elements—that only increases when we expand the window and decreases when we remove elements.

// Therefore, when the number of odd elements exceeds k, moving left forward can restore validity.

// Q4. What if k = 0?

// Then:

// atMost(nums, 0) - atMost(nums, -1)

// The atMost(nums, -1) case needs handling carefully because no window can contain -1 odd numbers.

// For this problem's constraints k >= 1, so the given solution is safe.

// Q5. Time Complexity?
// O(n)

// Why?

// right moves from left to right once, and left also moves only forward.

// So total pointer movements are linear.

// Space:
// O(1)

// No extra array/hashmap is required.

// Q6. Can we solve it using Prefix Sum?

// Yes.

// Convert every number:

// even → 0
// odd  → 1

// Then the problem becomes:

// Count subarrays whose sum is exactly k.

// We can use prefix sum + HashMap.

// Complexity:

// Time:  O(n)
// Space: O(n)

// Compared with your sliding-window solution:

// Time:  O(n)
// Space: O(1)

// So your sliding-window solution is better in space for this problem.

// Q7. Can we solve it using positions of odd numbers?

// Yes. Another elegant approach is to store the indices of all odd numbers and count how many even numbers can be included around every group of k consecutive odd numbers.

// But for interviews, your:

// AtMost(k) - AtMost(k-1)

// approach is usually easier to explain and implement.
