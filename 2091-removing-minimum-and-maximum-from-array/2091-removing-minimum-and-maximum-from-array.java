class Solution {
    public int minimumDeletions(int[] nums) {

        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find index of minimum and maximum
        for (int i = 1; i < n; i++) {

            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Make minIndex the smaller index
        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);

        // Case 1: Remove both from front
        int front = right + 1;

        // Case 2: Remove both from back
        int back = n - left;

        // Case 3: One from front, one from back
        int both = (left + 1) + (n - right);

        return Math.min(front, Math.min(back, both));
    }
}


// Interview Explanation
// English

// "First, I find the indices of the minimum and maximum elements. Then there are only three possible strategies: remove both from the front, remove both from the back, or remove the smaller-index element from the front and the other one from the back. I calculate the deletions for all three cases and return the minimum."

// Hindi

// "Pehle minimum aur maximum ke indexes find karunga. Uske baad sirf 3 possibilities hain: dono front se remove karein, dono back se remove karein, ya ek front aur ek back se remove karein. Teeno cases ki deletion count nikal kar minimum return karenge."

// 5. Follow-up Questions
// Q1. Why only 3 cases?

// Because deletion sirf front ya back se ho sakti hai.

// Minimum aur maximum ko remove karne ke liye:

// 1. Both → front
// 2. Both → back
// 3. One → front, one → back

// Iske alawa koi useful possibility nahi hai.

// Q2. Why use left and right?

// Taaki hume ye pata rahe ki min/max me se kaunsa index pehle aa raha hai.

// int left = Math.min(minIndex, maxIndex);
// int right = Math.max(minIndex, maxIndex);
// Q3. Can we solve it using sorting?

// Technically minimum/maximum find ho jayenge, but sorting unnecessary hai.

// Sorting:

// O(n log n)

// Simple traversal:

// O(n)

// So traversal is better.

// Q4. Is this Greedy?

// Yes, we calculate the minimum among all valid deletion strategies.

// Complexity
// Time:  O(n)
// Space: O(1)
