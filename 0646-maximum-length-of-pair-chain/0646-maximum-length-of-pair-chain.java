class Solution {
    public int findLongestChain(int[][] pairs) {
        int n = pairs.length;
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1])); // Sort pairs based on end value
        int count = 1;                  // Select first pair
        int lastEnd = pairs[0][1];
        for(int i=1;i<n;i++){                    // Traverse remaining pairs
            if(pairs[i][0]>lastEnd){           // If current pair can be added to the chain
                count++;
                lastEnd = pairs[i][1];    // Update last selected pair's end
            }
        }
        return count;
        
    }
}



// "I use a Greedy strategy similar to the Activity Selection problem. First, I sort all pairs by their ending value. Then I always select the pair that 
// finishes earliest because it leaves the maximum space for future pairs. I keep track of the end of the last selected pair. 
// If the current pair starts after the last selected pair ends, I include it in the chain. This greedy choice guarantees the maximum chain length."
