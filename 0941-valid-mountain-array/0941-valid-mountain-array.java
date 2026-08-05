class Solution {

    public boolean validMountainArray(int[] arr) {

        int n = arr.length;

        // Mountain must have at least 3 elements
        if (n < 3)
            return false;

        int i = 0;

        // Climb up the mountain
        while (i < n - 1 && arr[i] < arr[i + 1]) {
            i++;
        }

        // Peak cannot be first or last element
        if (i == 0 || i == n - 1)
            return false;

        // Walk down the mountain
        while (i < n - 1 && arr[i] > arr[i + 1]) {
            i++;
        }

        // If reached the end, it's a valid mountain
        return i == n - 1;
    }
}

// Interview Questions
// Q1. Why can't the peak be the first element?
// Because a mountain must first increase.
// Example:
// 5 4 3 2
// No increasing part.
// Not a mountain.

// Q2. Why can't the peak be the last element?
// Because a mountain must also decrease.
// Example:
// 1 2 3 4
// No decreasing part.
// Not a mountain.

// Q3. Why use < and > instead of <= and >=?
// Because the problem requires a strictly increasing and strictly decreasing sequence.
// Example
// 1 2 2 1
// Equal values break the mountain condition.

// Interview Explanation (25–30 sec)
// "I solve this using a two-pointer traversal. First, I move forward while the array is strictly increasing. The point where the increase stops is treated as 
// the peak. I then check that the peak is neither the first nor the last element. After that, I continue moving while the array is strictly decreasing.
// If I reach the last index exactly, the array is a valid mountain; otherwise, it is not."
