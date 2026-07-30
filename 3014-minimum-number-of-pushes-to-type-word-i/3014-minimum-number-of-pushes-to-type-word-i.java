class Solution {
    public int minimumPushes(String word) {
        int push =0;
        int n = word.length(); // O(n)
        for(int i=0;i<n;i++){   // Traverse every character
            push += (i/8)+1;   // Every group of 8 letters requires one extra push
        }

        return push;
        
    }
}



// Interview Explanation (English)

// "Since there are only 8 available keys, at most 8 letters can be assigned to the first position and typed with one push. After those positions are filled, the next 8 letters must occupy the second position and require two pushes, and so on. Therefore, I assign letters in groups of 8, where the required pushes are calculated using (index / 8) + 1. This greedy strategy guarantees the minimum total number of pushes."

// Common Interview Questions
// 1. Why Greedy?

// Because every key has one first position costing only one push. To minimize the total cost, we should fill all first positions before using any second positions.

// 2. Why don't we care about the actual characters?

// The letters are distinct, and we are free to remap them however we want. Only the number of letters matters, not which specific letters they are.

// 3. Why divide by 8?

// Because there are exactly 8 keys (2–9). Every group of 8 letters occupies the same push level.
