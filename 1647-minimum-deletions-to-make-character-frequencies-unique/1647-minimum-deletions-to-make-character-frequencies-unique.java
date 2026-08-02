class Solution {
    public int minDeletions(String s) {
        int[] freq = new int[26];  // Frequency array for 26 lowercase letters
        for(char ch : s.toCharArray()){   // Count frequency of each character
            freq[ch-'a']++;
        }
        HashSet<Integer> set = new HashSet<>();   // Stores frequencies that are already used
        int del = 0;
        for(int f : freq){     // Process frequency of every character
            while(f>0 && set.contains(f)){     // If current frequency is already used, // keep decreasing it until it becomes unique
                f--;   // Delete one occurrence
                del++;  // Count the deletion
            }
            if(f>0){     // Store the unique frequency (ignore frequency 0)
                set.add(f);
            }
        }
        return del;    // Minimum deletions required
        
    }
}


// Interview Explanation (English)
// "I first count the frequency of every character. Then I use a HashSet to keep track of frequencies that are already assigned. If the current frequency is already used, I keep decreasing it until it becomes unique or reaches zero, counting each decrease as one deletion. This greedy strategy ensures that I perform the minimum number of deletions while making all frequencies unique."

// Interview Questions
// Q1. Why Greedy?
// Because we always make the smallest possible reduction needed to make a frequency unique.

// Q2. Why HashSet?
// To check in O(1) time whether a frequency has already been used.

// Q3. Why reduce one by one?
// Reducing more than necessary would increase deletions, so decreasing by one until a unique frequency is found guarantees the minimum deletions.


// Complexity
// Time: O(n) (26 letters, so frequency adjustment is effectively constant overhead)
// Space: O(26) ≈ O(1)
