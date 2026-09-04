class Solution {

    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        // suffixMin[i] = minimum from i to n-1
        int[] suffixMin = new int[n];

        suffixMin[n - 1] = nums[n - 1];

        // Build suffix minimum
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Prefix maximum
        int prefixMax = Integer.MIN_VALUE;

        // Check every index
        for (int i = 0; i < n; i++) {

            prefixMax = Math.max(prefixMax, nums[i]);

            int instability = prefixMax - suffixMin[i];

            // First stable index
            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}



// 8. Can We Do It Without suffixMin[]?

// Yes. Since n <= 100, another simple approach is to calculate suffix minimum while traversing, but then we may repeatedly scan the right side.

// A better interview solution is the Prefix Max + Suffix Min approach because it clearly gives:

// Time  = O(n)
// Space = O(n)

// There is also an O(1) extra-space solution possible by first finding the global minimum and maintaining prefix maximum, because:

// For every i:

// min(nums[i...n-1])

// is not always the global minimum, so we cannot simply use the global minimum. Therefore, the suffix information is important for the straightforward O(n) solution.

// 9. Interview Explanation 🎤

// Interviewer ko aise explain karo:

// "For every index, I need the maximum value in the prefix [0...i] and the minimum value in the suffix [i...n-1].
// A brute-force solution would calculate these values repeatedly and take O(n²) time.
// To optimize, I precompute the suffix minimum in an array. Then I traverse from left to right while maintaining the prefix maximum.
// For each index, I calculate prefixMax - suffixMin[i]. If this value is less than or equal to k, I immediately return that index because we need the smallest stable index.
// The time complexity is O(n) and space complexity is O(n)."

// Short version

// "I precompute suffix minimums and maintain prefix maximum while traversing. The first index where prefixMax - suffixMin[i] <= k is the answer."

// 10. Interview Follow-Up Questions
// Q1. Why are you using a suffix array?

// Answer:

// Because for every i, we need:

// min(nums[i...n-1])

// Precomputing it lets us get this value in O(1) for every index.

// Q2. Why can't you only maintain prefix maximum?

// Because we also need:

// min(nums[i...n-1])

// Prefix maximum alone cannot tell us the minimum on the right side.

// Q3. Why do you return immediately?
// if (instability <= k) {
//     return i;
// }

// Because indexes are checked from left to right.

// So the first valid index is automatically the smallest stable index.

// Q4. What is the time complexity?
// Build suffixMin → O(n)
// Traverse array   → O(n)

// Total → O(n)
// Q5. Space complexity?
// suffixMin[] → O(n)

// So:

// Space = O(n)
// Q6. What if k = 0?

// Then we require:

// prefixMax == suffixMin

// Only those indices where the two values are equal will be stable.

// Example:

// nums = [0]
// k = 0
// prefixMax = 0
// suffixMin = 0

// 0 - 0 = 0 <= 0

// Answer = 0.

// Q7. What if no index is stable?

// Return:

// return -1;
// Q8. Can this be solved using binary search?

// Not naturally/cleanly. The instability score is not guaranteed to be monotonic across indices, so standard binary search cannot safely be applied.

// Interview mein main O(n) prefix + suffix approach choose karunga.
