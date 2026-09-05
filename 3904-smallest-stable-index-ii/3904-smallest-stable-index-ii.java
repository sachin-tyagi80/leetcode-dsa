class Solution {

    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        // suffixMin[i] = minimum element from i to n-1
        int[] suffixMin = new int[n];

        // Last element
        suffixMin[n - 1] = nums[n - 1];

        // Build suffix minimum array
        for (int i = n - 2; i >= 0; i--) {

            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Maximum from 0 to current index
        int prefixMax = Integer.MIN_VALUE;

        // Find smallest stable index
        for (int i = 0; i < n; i++) {

            // Update prefix maximum
            prefixMax = Math.max(prefixMax, nums[i]);

            // Calculate instability
            long instability = (long) prefixMax - suffixMin[i];

            // Stable index
            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}


// Interview mein kaise explain karna hai 🎤

// Interviewer bole "Explain your approach."

// Aap bol sakte ho:

// "For every index, I need the maximum value from the prefix and the minimum value from the suffix. A brute-force solution would repeatedly calculate these values and take O(n²). Since n can be 100000, I optimize it using a suffix minimum array. I first calculate the minimum value from every index to the end. Then I traverse from left to right while maintaining the prefix maximum. For each index, I calculate prefixMax minus suffixMin[i]. If it is less than or equal to k, I return that index because it is the smallest stable index."

// Short interview version

// "I precompute suffix minimums and maintain the prefix maximum while traversing. The first index where prefixMax - suffixMin[i] <= k is the answer. Complexity is O(n) time and O(n) space."

// 9. Interview Follow-up Questions
// Q1. Why can't we use brute force?

// Because n can be 100000, and brute force is:

// O(n²)

// which is too slow.

// Q2. What does suffixMin[i] represent?
// suffixMin[i] = min(nums[i], nums[i+1], ..., nums[n-1])
// Q3. What does prefixMax represent?

// At index i:

// prefixMax = max(nums[0], nums[1], ..., nums[i])
// Q4. Why do we traverse from right to left for suffix minimum?

// Because:

// suffixMin[i]

// depends on:

// suffixMin[i + 1]

// So right-to-left traversal naturally calculates it.

// Q5. Why return immediately?

// Because:

// i = 0 → 1 → 2 → 3 → ...

// Hum indices increasing order mein check kar rahe hain.

// Therefore first stable index = smallest stable index.

// Q6. What happens if no stable index exists?

// After checking all indexes:

// return -1;
// Q7. Can we use Arrays.sort()?

// No. Sorting destroys the original index relationship.

// Problem specifically needs:

// nums[0...i]
// nums[i...n-1]

// So original order is important.
