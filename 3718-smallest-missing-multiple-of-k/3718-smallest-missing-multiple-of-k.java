import java.util.*;

class Solution {
    public int missingMultiple(int[] nums, int k) {

        HashSet<Integer> set = new HashSet<>();

        // Store all numbers
        for (int num : nums) {
            set.add(num);
        }

        // Check multiples of k
        int multiple = k;

        while (set.contains(multiple)) {
            multiple += k;
        }

        return multiple;
    }
}


// Interview Explanation
// English
// "I store all elements in a HashSet for O(1) average lookup. Then I start from k and keep checking k, 2k, 3k.... The first multiple that is not present in the set is the smallest missing multiple."
// Hindi
// "Main array ke elements ko HashSet me store karta hoon, jisse existence check fast ho jata hai. Phir k se start karke uske multiples check karta hoon. Jo pehla multiple Set me nahi milta, wahi answer hai."

// 6. Follow-up Questions
// Q1. Why HashSet?
// Because:

// set.contains(x) → O(1) average

// Array me search karenge to O(n) lagega.

// Q2. Can we solve without HashSet?

// Yes, constraints small hain. Boolean array bhi use kar sakte hain.

// boolean[] present = new boolean[101];

// for (int num : nums) {
//     present[num] = true;
// }

// int x = k;

// while (x <= 100 && present[x]) {
//     x += k;
// }

// return x;

// But HashSet solution more general hai.

// Q3. Why do we start from k, not 1?

// Because question specifically asks for positive multiple of k.

// So sequence is:

// k, 2k, 3k, 4k...
// Complexity

// HashSet approach:

// Time:  O(n + m)
// Space: O(n)

// where m is the number of multiples checked.
