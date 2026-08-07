class Solution {

    public boolean checkInclusion(String s1, String s2) {

        if (s1.length() > s2.length()) {
            return false;
        }

        int[] need = new int[26];
        int[] window = new int[26];

        // Frequency of s1
        for (char ch : s1.toCharArray()) {
            need[ch - 'a']++;
        }

        int left = 0;

        for (int right = 0; right < s2.length(); right++) {

            window[s2.charAt(right) - 'a']++;

            // Window size greater than s1
            if (right - left + 1 > s1.length()) {

                window[s2.charAt(left) - 'a']--;
                left++;
            }

            // Compare both frequency arrays
            if (Arrays.equals(need, window)) {
                return true;
            }
        }

        return false;
    }
}


// Why Sliding Window?
// Every valid substring must have
// length = s1.length()
// So instead of checking every possible substring,
// we maintain one fixed-size window and slide it.
// This reduces repeated work.

// Interview Questions
// Q1. Why Sliding Window?
// Because every permutation has the same length as s1.
// So only windows of that size need to be checked.

// Q2. Why use frequency arrays?
// The order of characters doesn't matter in a permutation.
// Only the frequency of each character matters.

// Q3. Why compare arrays?
// If both frequency arrays are identical,
// the current window contains exactly the same characters as s1, meaning it is a permutation.
    
// Q4. Why remove the left character?
// To maintain a fixed window size equal to s1.length().
    
// Q5. Time Complexity
// Building frequency arrays: O(m), where m = s1.length()
// Sliding window over s2: O(n)
// Comparing two arrays of size 26: O(26) = O(1)

// Overall:

// O(n)

// where n = s2.length().

// Q6. Space Complexity

// Two frequency arrays of size 26.

// O(1)
// Interview Explanation (30 seconds)

// "I solve this using a sliding window because every permutation of s1 has the same length as s1. I first store the character frequencies of s1. 
// Then I slide a fixed-size window of length s1.length() over s2, updating the frequency counts as the window moves. Whenever the window's frequency
// matches the frequency of s1, it means the current substring is a permutation of s1, so I return true. If no window matches, I return false. 
// The solution runs in O(n) time with O(1) extra space."
